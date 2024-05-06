# OAuth 2.0

## Servers

Client server: Requests stuff.

Authorization server: Makes sure the client is authorized.

Resource server: Holds the resources that the user wants access to.

## Opaque Tokens

Opaque tokens contain a string of random characters that are only understood by the Authorization server.
They're more secure than JWT's, so use them when you want to secure (and slow down) an application. 
A client might read from a JWT to know the user's role or id. With opaque tokens you couldn't do that.

## Grants and Flows

A grant is a method of acquiring an access token. 
A flow is the process that implements one or more grant types.

### Authorization Code (Web/Mobile app)

It's when you exchange an authorization code for a token.
This is only used for SSR apps (which they call regular web apps).

https://images.ctfassets.net/cdy7uua7fh8z/7mWk9No612EefC8uBidCqr/411437ff3eb3d5a632829c90530c3806/auth_border.png

#### Authorization Code Flow with PKCE (Proof Key for Code Exchange)

Meant for clients that can't store secrets securely  (either native mobile/desktop or CSR)

It uses a verifier or "proof key" and a challenge, a transformation of that verifier.
PKCE uses both of those two things to make sure the client is legit.

https://images.ctfassets.net/cdy7uua7fh8z/3pstjSYx3YNSiJQnwKZvm5/33c941faf2e0c434a9ab1f0f3a06e13a/auth-sequence-auth-code-pkce.png

### Implicit 

1. Simplified version for browser clients  
2. You directly return the access token 

### Resource Owner Password Credentials 

Less secure, used when other flows aren't viable.

1. User provides the username and password directly to the app
2. The client app then uses those credentials to obtain the token 

### Client Credentials (Server->Server)

### Refresh Token

### Extension Grant Type

Make your own grant type.

## Resources 

[OAuth 2.0 Security Best Practices](https://datatracker.ietf.org/doc/html/draft-ietf-oauth-security-topics)
[OAuth 2.0 For Browser Based Apps](https://datatracker.ietf.org/doc/html/draft-ietf-oauth-browser-based-apps-10)

[Implementation Guidelines for Browser-Based Apps](https://github.com/spring-projects/spring-authorization-server/issues/297)
