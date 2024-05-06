# Servlet

Servlet's are a JEE technology. Tomcat is a servlet container. 
The following is specific to servlets and not spring.

## HttpServletRequest

### contextPath 

It's not a spring thing, it's the part of the URL that specifies an application on a server.

http://example.com/foo
http://example.com/bar

It's helpful if you have one server serving multiple apps.

### servletPath

The part of the URL that corresponds to the servlet that will handle the request, as defined in the servlet mapping.

If you have a servlet mapped to /products, then a URL like... 

http://example.com/foo/products

will have /products as its servlet path

### pathInfo

If the same servlet (/products) is designed to handle additional path information, a URL like... 

http://example.com/myapp/products/123 

Would have /123 as its pathInfo, indicating a specific product ID.

### queryString

To query details about a specific product with ID 123 a URL could be... 

http://example.com/myapp/products?id=123. 

Here, the queryString is id=123, providing the product ID to the servlet.
