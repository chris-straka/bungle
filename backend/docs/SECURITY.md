# Security Notes

## Servlet Architecture

1. DelegatingFilterProxy -> Connects servlet lifecycle with spring
   application context
2. FilterChainProxy -> Contains SecurityFilterChains (SFCs) and forwards requests to them
3. SecurityFilterChain -> Has all the security filters I want to apply
4. SecurityFilters -> middleware for authentication, authorization, CSRF, etc.

## Spring Security Authentication Architecture

/login

1. HTTP Request Sent
2. Authentication object is created with isAuthenticated() set to False
3. That AO is passed to an AuthenticationManager (often a ProviderManager)
4. That PM will use a provider like DaoAuthenticationProvider to validate the request.
5. The DAO provider will use a DAO like UserDetailsService to fetch any UserDetails from the DB that has that username.
6. The PM checks if the passwords match. If they do, it updates the Authentication object.
7. The updated AO has isAuthenticated() set to True and it's stored in the SecurityContextHolder

/authenticated-route

1. Filter checks the SecurityContext and looks for isAuthenticated()

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
