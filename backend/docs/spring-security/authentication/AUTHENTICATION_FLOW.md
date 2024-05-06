# Servlet Authentication Flow

POST /login

1. An Authentication Object (AO) is created by the AbstractAuthenticationProcessingFilter (AAPF)

- At this point the AO.isAuthenticated() === False

2. The AO is passed to an AuthenticationManager (AM) which is often implemented by the ProviderManager (PM)
3. The PM uses an AuthenticationProvider (AP) like DaoAuthenticationProvider (DAP) to validate the request.
4. The DAP uses a Data Access Object (DAO) like UserDetailsService (UDS) to fetch UserDetails (UD) from the DB.
5. The password from the HTTP request is matched against the password from the UD.

If the paswords match

1. SessionAuthenticationStrategy is notified of new login
2. SCH is populated with the AO.isAuthenticated() === True
3. RememberMe.loginSuccess()
4. ApplicationEventPublished
5. ApplicationSuccessHandler

If the passwords don't match

1. SCH is cleared out.
2. RememberMe.loginFail()
3. AuthenticationFailureHandler

## Servlet Authenticated Route Flow

/authenticated-route

1. Filter checks the SecurityContext and looks for AO.isAuthenticated()
2. If False, AuthenticationEntryPoint decides whether to redirect to login or send 401.
