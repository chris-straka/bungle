# Security

## Adaptive One Way Functions For Password Hashing

SHA-256 is no longer secure, modern hardware can hash billions per second.
We now use adaptive one-way functions (bcrypt, argon2, etc) that are resource intensive.
"Adaptive" meaning they have a "work factor" that scales with better hardware
Initial logins are slow with one-way funcs, so you need sessions/tokens for things to stay fast.

## Servlet Architecture

1. DelegatingFilterProxy -> Connects servlet lifecycle with spring application context
2. FilterChainProxy -> Contains SecurityFilterChains (SFCs) and forwards requests to them
3. SecurityFilterChain -> Has all the security filters I want to apply
4. SecurityFilters -> middleware for authentication, authorization, CSRF, etc.

## Resources

[How to handle Auth](https://stackoverflow.com/questions/75571606)
[Helpful security stuff](https://www.youtube.com/watch?v=fZwYYD7La4I)
