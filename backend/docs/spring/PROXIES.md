# How Spring Uses Proxies

## JDK Proxies

These proxies are features of the JDK and are preferred whenever possible.

But they only work when you want to create a proxy for an interface (common in AOP).

## CGLIB Proxy

CGLIB is a java bytecode generation library.

Spring CGLIB is used whenever you want to create a proxy for a class (instead of an interface).
It's frequently used in @Configuration, and stuff.
