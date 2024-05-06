# Authorization

Spring Security has request based and method based authorization.
Authorization is placed last in the SFC by default.
The AuthorizationFilter AF runs on every dispatch (see jakarta notes for dispatch info).
A lot of Authorization in spring security has to do with the SFC's authorizeHttpRequests().

requestMatcher() then authorizationRequestMethod() like hasRole(), hasAuthority(), permitAll()
hasRole() is the same as hasAuthority() but it prepends ROLE\_ for you.
access() uses your own custom AM to authorize that path

```java
hasAuthority('ROLE_ADMIN') // hasRole('ADMIN')
```

## GrantedAuthority

It's an interface with a getAuthority() method that returns a String like ROLE_FOO.

SimpleGrantedAuthority is an implementation provided by spring that turns Strings into GAs.

## AuthorizationManager AM

It's an interface that determines whether an Authentication object has access to another object.
The only difference between check() and verify() is the latter throws an exception.

```java
// inside check()
Authentication.GrantedAuthority.getAuthority() // returns ROLE_FOO
```

AuthorizationDecisions can be positive (granted), negative (denied) or null (abstain).
The RequestMatcherDelegatingAuthorizationManager (RDAM) matches the request to a suitable AM delegate.

## SecurityMatcher

A security matcher applies the http configuration to a SPECIFIC route instead of every route.

```java
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
  http
    .securityMatcher("/api/**")
    .authorizeHttpRequests(auth -> auth
      .requestMatchers("/user/**").hasRole("USER") // /api/user/**
    )
    .formLogin(withDefaults())
    .build();
}
```

## Method Security

```java
// putting this on a @Configuration class
@EnableMethodSecurity 

// Enables all this
@PreAuthorize, @PostAuthorize, @PreFilter, @PostFilter
```

