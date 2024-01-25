# Security Notes

## Spring Session

When Spring Session talks about "container-neutral" solutions, they're not talking about docker.
They're talking about servlet containers (Tomcat, Jetty).

Spring's default session management ties the session to the Tomcat servlet container (server memory)
This means you can't store the session somewhere else by default, like in Redis.

## Servlet Architecture

1. DelegatingFilterProxy -> Connects servlet lifecycle with spring
   application context
2. FilterChainProxy -> Contains SecurityFilterChains (SFCs) and forwards requests to them
3. SecurityFilterChain -> Has all the security filters I want to apply
4. SecurityFilters -> middleware for authentication, authorization, CSRF, etc.

## Spring Security Authentication Architecture

/login

1. HTTP Request Sent
2. An Authentication object is created by the AbstractAuthenticationProcessingFilter (AO.isAuthenticated() === False)
3. That AO is passed to an AuthenticationManager (often a ProviderManager)
4. The PM will use a provider like DaoAuthenticationProvider to validate the request.
5. The DAO provider will use a DAO like UserDetailsService to fetch UserDetails from the DB if the usernames match.
6. The PM then checks if the passwords match. If they do, it updates the Authentication object (AO.isAuthenticated() === True)
7. The updated AO is then stored in the SecurityContextHolder for later use.

/authenticated-route

1. Filter checks the SecurityContext and looks for isAuthenticated()
2. If False, AuthenticationEntryPoint decides whether to redirect to login or send 401.

## JWT stuff

From Ben Awad (see references)

"""
Putting a JWT token in localstorage makes it obtainable for hackers in XSS attacks -> Object.keys(localStorage)
However, hiding it from JS by putting it in an HTTP Only token doesn't help either -> fetch(url)
What can help, is form validation and reprompting the user for their password before making serious requests
"""

## Resources

[How to handle Auth](https://stackoverflow.com/questions/75571606)

[Ben Awad conversation on Auth](https://www.youtube.com/watch?v=vq861XoZI9k)
