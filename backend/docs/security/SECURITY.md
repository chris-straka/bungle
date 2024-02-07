# Security Notes

## Adaptive One Way Functions For Password Hashing

SHA-256 is no longer secure, modern hardware can hash billions per second.
We now use adaptive one-way functions (bcrypt, argon2, etc) that are resource intensive. 
"Adaptive" meaning they have a "work factor" that scales with better hardware 
Initial logins are slow with one-way funcs, so you need sessions/tokens for things to stay fast.

## Servlet Architecture

1. DelegatingFilterProxy -> Connects servlet lifecycle with spring
   application context
2. FilterChainProxy -> Contains SecurityFilterChains (SFCs) and forwards requests to them
3. SecurityFilterChain -> Has all the security filters I want to apply
4. SecurityFilters -> middleware for authentication, authorization, CSRF, etc.

## Spring Security Authentication Architecture

POST /sessions

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

## Resources

[How to handle Auth](https://stackoverflow.com/questions/75571606)

[Helpful security stuff](https://www.youtube.com/watch?v=fZwYYD7La4I)

[Do login forms need CSRF tokens?](https://stackoverflow.com/questions/6412813)
