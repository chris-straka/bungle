# Servlet

## Servlet Architecture

1. DelegatingFilterProxy -> Connects servlet lifecycle with spring application context
2. FilterChainProxy -> Contains SecurityFilterChains (SFCs) and forwards requests to them
3. SecurityFilterChain -> Has all the security filters I want to apply
4. SecurityFilters -> middleware for authentication, authorization, CSRF, etc.
