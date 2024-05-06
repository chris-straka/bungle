# Authentication

There's three authentication options that look good to me.

1. Sessions (with redis, cookies and csrf) 
2. JWT with OWASP recommendations 
3. OIDC

There's a section for form, basic and digest but...

- Form is not for APIs
- Basic sends the user/pass with every request
- Digest is deprecated

## JWT

There's two DIY methods to authenticate a CSR app using JWTs.

1. You write your own custom filter that runs before anything else and handles authentication.
2. You use Spring Security's OAuth2.0 support to create your own self signed custom JWTs.

In the real world, I think everyone does this.

1. OAuth 2.0 pointing to a third party authorization server (Cognito, Okta, Auth0, Keycloak).
2. A DIY BFF architecture, with cookies from client -> BFF and JWTs from BFF -> resource server.
3. OIDC

### OWASP Method

Send a JWT and a cookie.

[OWASP Cheat Sheet JJWT](https://cheatsheetseries.owasp.org/cheatsheets/JSON_Web_Token_for_Java_Cheat_Sheet.html#token-sidejacking)

[Joe Grandja's guidelines for SPA apps](https://github.com/spring-projects/spring-authorization-server/issues/297)

[Toerktumlare's answer for how to do auth](https://stackoverflow.com/a/77581289)

[g00glen00b](https://www.reddit.com/r/SpringBoot/comments/1afqizl/comment/kocs7dj/?utm_source=share&utm_medium=web3x&utm_name=web3xcss&utm_term=1&utm_content=share_button)

[Why use JWT instead of server side sessions](https://www.reddit.com/r/node/comments/171fytv/why_use_jwt_instead_of_serverside_sessions/)
