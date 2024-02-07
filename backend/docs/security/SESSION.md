# Session 

## Spring Session

When Spring Session talks about "container-neutral" solutions, they're not talking about docker containers.
They're talking about servlet containers (Tomcat, Jetty).

Spring's default session management ties the session to the Tomcat servlet container (server memory).
If you want to store the sessions somwhere else, like in Redis, you need the spring-session package.

## HttpSessionCsrfTokenRepository

This stores the CSRF token on the server under the user's session.

If it's an SSR app, it will embed the CSRF token in every page it sends back to the user.
The user returns the token via a X-CSRF-TOKEN header to authenticate their request.

If it's an API app, then you need to manually send the client a CSRF token on an endpoint.
The user then sends it back in an HTTP request header called X-CSRF-TOKEN.

## Session Fixation Attach

1. The attacker gives the user a fake sessionID 
2. The user signs in and sends the server the attacker's sessionID 
3. The server uses the sessionID provided by the user without refreshing it

So refresh the sessionID after the user logs in.
