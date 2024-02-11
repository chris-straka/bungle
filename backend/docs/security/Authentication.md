# Authentication

I think JDBC authentication is more common if you intend to use JDBC raw SQL queries in your app.
If you intend to use Spring JPA, then implementing your own UserDetailsService is more common.

## Architecture

POST /login

1. HTTP Request Sent
2. Authentication object created by AbstractAuthenticationProcessingFilter (AO.isAuthenticated() === False)
3. AO is passed to AuthenticationManager (often ProviderManager)
4. PM uses a provider like DaoAuthenticationProvider to validate request.
5. DAOAP uses a DAO to fetch UserDetails from the DB if the usernames match.
    - The DAO in this case is an implementation of the UserDetailsService
    - The implementation

6. The PM then checks if the passwords match. If they do, it updates the Authentication object (AO.isAuthenticated() === True)
7. The updated AO is then stored in the SecurityContextHolder for later use.

/authenticated-route

1. Filter checks the SecurityContext and looks for isAuthenticated()
2. If False, AuthenticationEntryPoint decides whether to redirect to login or send 401.
