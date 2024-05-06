# Sessions in Spring Security

## HttpSessionCsrfTokenRepository

This stores the CSRF token on the server under the user's session.

If it's an SSR app, it will embed the CSRF token in every page it sends back to the user.
The user returns the token via a X-CSRF-TOKEN header to authenticate their request.

If it's an API app, then you need to manually send the client a CSRF token on an endpoint.
The user then sends it back in an HTTP request header called X-CSRF-TOKEN.
