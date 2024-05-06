# Servlet Authentication Architecture

## SecurityContextHolder

The SecurityContextHolder (SCH) is a unified way of storing/retrieving the SecurityContext (SC).
We need it because it helps us customize how we store the SC, which might change depending on the app.

By default, it stores the SC on the thread, not the heap.
Storing it on the thread makes it easier to access at every level of the app (controllers, services, etc).
If we stored it on the heap, we would have to pass it as a parameter across all our layers.

You can store the SecurityContext globally across the entire application using MODE_GLOBAL.
This makes the SecurityContext available on all threads (great for desktop apps).
This is not good for web servers because each thread has a different user/SC.

```java
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
public class Controller {
  // Spring Security automatically passes in the SCH for us
  public void foo(SecurityContextHolder sch) {
    sch.getContext();
    sch.getDeferredContext();
    sch.clearContext();
    sch.createEmptyContext();
    sch.setContext(null);

    sch.setStrategyName(sch.MODE_GLOBAL); // store the sc globally
    sch.getContextHolderStrategy();
    sch.getInitializeCount(); // for debugging
  }
}
```

### SecurityContext

It holds the Authentication object

```java
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class Controller {
  public void foo(){

  }
}
```

#### ThreadLocal

To store something on the thread (like SecurityContextHolder does) you can do this...

```java
ThreadLocal<Foo> foo = new ThreadLocal<>();

foo.set(new Foo());    // Place value on the current thread
Foo myFoo = foo.get(); // Getting the value back
```

##### Authentication

Holds the principal, credentials (password), and authorities.

###### GrantedAuthority

They're application wide roles for different users.
They're not meant to be placed on the domain object.

### AuthenticationManager

It's a functional interface with an authenticate() method.
It defines how Spring Security's Filters perform authentication.
It returns an Authentication object that gets stored in the SC.

#### ProviderManager

It's the most common implementation for the AuthenticationManager interface.
It has a list of AuthenticationProviders (AP) and each AP has a chance to authenticate().
This is how you can have SAML, OAuth and user/password in one API.

A provider manager can have an AuthenticationManager as its parent.
It will point to it if there is no suitable AP for the authentication() request.

Multiple PM's might share the same parent (if you have multiple SFC's)

The PM wipes out credentials after a successful authentication.

##### AuthenticationProvider

It's responsible for authenticating a user.
Examples include DaoAuthenticationProvider, JwtAuthenticationProvider.
The former requires the UserDetailsService.

### AuthenticationEntryPoint AEP

It's an interface with methods for requesting credentials from a client.
It's only invoked whenever the client has requested a protected route without being logged in first.
There's multiple implementations of it depending on your config i.e. httpBasic(), formLogin()

LoginUrlAuthenticationEntryPoint
BasicAuthenticationEntryPoint

### AbstractAuthenticationProcessingFilter AAPF

It receives authentication HTTP requests and turns them into Authentication Objects (AO).
The type of AO you get depends on which subclass of AAPF you use.

The UsernamePasswordAuthenticationFilter subclass will create a UsernamePasswordAuthenticationToken AO.
