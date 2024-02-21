# CSRF 

Cookies are always sent in the Cookie HTTP request header.
Other headers like X-CSRF-TOKEN are only sent when specified.

Can be prevented with tokens (sync token pattern) or SameSite

## Synchronizer Token Pattern

1. The server sends the client a CSRF token
2. The client sends it back in an HTTP request

The server does not look in the cookies for the CSRF token (defeats the point)
The server looks in the url query params, request headers, request body, etc.

You can add the CSRF token as a cookie (without HttpOnly) if you want.
This works because the server does not check cookies for the CSRF token. 
You would do this to make timeouts easier to manage. 
The client would grab the CSRF token from the cookie and then send it like before.

The reason why this is not the default is explained in the docs but I don't understand it.
https://docs.spring.io/spring-security/reference/features/exploits/csrf.html#csrf-considerations-timeouts

## Login CSRF (Forging login Requests)

When an attacker tricks a victim into logging into an attacker's account.

The victim clicks on a malicious link crafted by an attacker. 
That link has the attacker's username and password for a legit site.
The victim clicks the link, and then is signed into the attacker's account. 
The victim then enters sensitive information that an attacker can view later.

To prevent this, you have to generate a CSRF token whenever the user visits your login page.
The server has to generate a CSRF token, store it somewhere (redis) and send it to the client.
This happens on every login page refresh if you're not careful.

If you tie the CSRF token to the user's session, you could create an unexpected timeout.
This can happen when...

1. The user visits the login page 
2. They're given a session cookie and a CSRF token
3. The user waits long enough for the session cookie to expire
4. They then try to login and get thrown a timeout error

## Logout CSRF (Making the user logout)

The attacker makes the user logout, then they follow up with a login CSRF.

## SameSite=Strict

1. If the user has an older browser, this won't work
2. This is a spring session thing, not a spring security thing.
3. Your API can no longer accept requests from different origins 
    - email.foo.com (is not the same origin as) email.foo.com

Marketing websites can't link existing users to your site either (their GET will send cookies).

SameSite=Lax allows "safe" HTTP methods from other sites to your site (GET, HEAD, OPTIONS)
However, it also allows POST requests via form submissions (not AJAX) coming from other sites 
