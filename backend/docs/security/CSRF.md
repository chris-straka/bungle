# CSRF 

The CSRF token is tied to the session. 

If an attacker visited a page to grab a CSRF token, it would be tied to their session and not the victim's. 

## Protections

Spring has two ways to protect against CSRF attacks

### Synchronizer Token Pattern

This is when the server sends the client a CSRF token in the request
Then the client sends it back to the server in their HTTP request (NOT as a cookie!)

### Setting SameSite: Strict

SameSite: Lax allows "safe" HTTP methods from other sites like GET, HEAD, OPTIONS.

#### Issues with Relying purely on SameSite: Strict 

1. If the user has an older browser, this won't work
2. If your API needs to accept requests from other domains, this prevents that.
