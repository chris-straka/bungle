# Servlet

Servlet's are a JEE technology. Tomcat is a servlet container. 
Spring creates an ApplicationContext, then a DispatcherServlet.
Spring registers filters with the container, not the servlet.

```java
import javax.servlet.*;
import javax.servlet.http.*;

public class MyServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException {
        // Your code here
    }
}
```

- GenericServlet
- HttpServlet

There's different kinds of Servlets, spring uses the DS. 
You can create other servlets inside your spring MVC app and use them.
You might do this for legacy apps that were built using another servlet.

## Filters

They're usually ran for every request/response, but you can limit them to certain urls.
The lower the order, the earlier the filter runs (order can even be negative).
chain.doFilter() runs the next filter in terms of the order

```java
@Component
@Order(-10)
public class TransactionFilter implements Filter {

    @Override
    public void doFilter(
      ServletRequest request, 
      ServletResponse response, 
      FilterChain chain) throws IOException, ServletException {
 
        HttpServletRequest req = (HttpServletRequest) request;
        LOG.info(
          "Starting a transaction for req : {}", 
          req.getRequestURI());
 
        chain.doFilter(request, response);
        LOG.info(
          "Committing a transaction for req : {}", 
          req.getRequestURI());
    }

    // other methods 
}
```
