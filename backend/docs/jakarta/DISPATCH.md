# Dispatch Types

Dispatch types are a web server concept, defined by the Servlet API, which is now a part of Jakarta EE.
They control how a servlet handles different kinds of request processing scenarios in a web application.
These dispatch types help manage request flow within server-side handling. 

It's especially helpful when dealing with servlets and JavaServer Pages (JSP).

Here's a brief overview:

```
REQUEST: A direct request to a resource.
FORWARD: Internal server forwarding to another resource.
INCLUDE: Including content from another resource in the response.
ERROR: Handling an error triggered during request processing.
Spring Security utilizes this concept to ensure proper handling of security contexts across these different processing scenarios.
```

## Examples (Spring)

This has a REQUEST and a FORWARD dispatch.

```java
@Controller
public class MyController {
    // REQUEST
    @GetMapping("/endpoint")
    public String endpoint() {
        // FORWARD
        return "endpoint"; 
    }
}
```

This has a REQUEST and an ERROR dispatch

```java
@Controller
public class MyController {
    // REQUEST
    @GetMapping("/endpoint")
    public String endpoint() {
        // FORWARD
        throw new UnsupportedOperationException("unsupported");
    }
}
```
