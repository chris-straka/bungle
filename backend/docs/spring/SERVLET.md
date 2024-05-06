# Servlet

Servlets are classes that get created in the servlet container (Tomcat)
They run in a "servlet container", and the number of running servlets is limited by RAM.
There's different kinds of Servlets (Generic, HTTP, and DispatcherServlet).
Spring uses the DispatcherServlet, which is created by the container (web.xml).

## Create your own

You can create other servlets inside your spring MVC app and use them.
You might do this for legacy apps that were built using a different servlet.

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
