# Persisting Authentication

## SecurityContext

SecurityContext (SC) is an interface
SecurityContextHolder (SCH) is a unified way to get/store the current SC.
SecurityContextRepository (SCR) is an interface responsible for storing the SC between requests.
DelegatingSecurityContextRepository (DSCR) is the default implementation of the SCR.

DSCR can store it in any number of places, by default it stores it in 1 & 3.

1. HttpSessionSecurityContextRepository stores it in the HttpSession
2. NullSecurityContextRepository doesn't store it anywhere
3. RequestAttributeSecurityContextRepository stores it in an "HTTP request attribute"

So in the end, the SC is stored in the HTTP Session by default.

### HTTP Request Attributes

A request attribute is a property that only exists for the duration of a request.
It's a servlet API thing, and is accessible off of HTTPServletRequest.

## SecurityContextPersistenceFilter (deprecated)

SecurityContextPersistenceFilter (SCPF) will load and store the SC between requests.
It's been replaced by the SecurityContextHolderFilter.
As of spring security 6 it's not set by default.

## SessionManagementFilter (SMF)

A user might start a request unauthenticated but then become authenticated later in that same request.
This usually happens with non-interactive authentication flows (remember-me, pre-authentication).

The SMF looks at the SCR and compares that to the SC in the current SCH. 
If the SCR is empty and the SCH isn't, then the user has authenticated somewhere.
The SMF will then run the SessionAuthenticationStrategy (SAS) which will...

- activate session-fixation protection mechanisms 
- check for multiple concurrent logins

They're moving away from SMF because it reads the HTTPSession on every request.
Now the authentication mechanisms themselves will run the SAS stuff.

## SecurityContextHolderFilter

It's a filter that loads the SC by reading from the SCR. 
It does not store the SC in the SCH, you have to do that yourself.

```java
SecurityContextHolder.setContext(securityContext);
securityContextRepository.saveContext(securityContext, httpServletRequest, httpServletResponse);
```

## SecurityContextHolderStrategy

The SecurityContextHolderStrategy (SCHS) tells Spring where to store the SC.
By default, the SCHS sets the strategy for the class loader, not the application context.
This can be problematic if you have multiple application contexts that require different strategies.
To wire the SCHS to an application context you can do this.

```java
public class SomeClass {

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    public void someMethod() {
        UsernamePasswordAuthenticationToken token = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getUsername(), loginRequest.getPassword()); 
        Authentication authentication = this.authenticationManager.authenticate(token);
        // ...
        SecurityContext context = this.securityContextHolderStrategy.createEmptyContext(); 
        context.setAuthentication(authentication); 
        this.securityContextHolderStrategy.setContext(context); 
    }
}
```

There are multiple reasons why you might have different application contexts running in the same JVM.

- You might have a modular application where you want to configure different parts separately
- You might be running more than one application in the same JVM for resource optimization.
