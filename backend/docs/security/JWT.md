# JWT

## Frontend

Paraphrasing Ben Awad...

"""
If someone can run JS on your website via an XSS attack, it doesn't matter where you store your JWT.
Storing it in localstorage is not safe -> Object.keys(localStorage)
Storing it in a hardened cookie (HTTP, SameSite) is is not safe -> fetch(url)
What does help, is form validation to prevent XSS altogether and more password prompts.
"""

The fetch(url) is an SSRF attack, rather than CSRF.

Access token leakage is when you lose your token from an XSS attack.
Access token replay is when that stolen token is used again illegitimately.

[Ben Awad conversation on Auth](https://www.youtube.com/watch?v=vq861XoZI9k)

## Resources

[JWT vs Server Side Sessions](https://www.reddit.com/r/node/comments/171fytv/why_use_jwt_instead_of_serverside_sessions/)

[Access and refresh tokens in spring boot, is this safe?](https://stackoverflow.com/questions/77333385)

[Implementation Guidelines for Browser-Based Apps](https://github.com/spring-projects/spring-authorization-server/issues/297)

[Spring boot with refresh token](https://www.bezkoder.com/spring-boot-refresh-token-jwt)

[Spring boot JWT authentication](https://www.bezkoder.com/spring-boot-jwt-authentication/)
