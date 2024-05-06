# Filters

A filter is a middleware that runs for every request/response. 
Filters are registered with the servlet container and not the servlets.
Any Filter that is a Spring bean is automatically registered.
You can use FilterRegistrationBean for more control and customization.

The servlet container has a default filter chain (DFC) that you can add filters to.
Spring (not spring security) has a DelegatingFilterProxy (DFP) filter.
You can add it to the DFC, and put any number of filters inside of it.

Filters run for every request/response.

```java
import jakarta.servlet.annotation.WebFilter;

@Component      // registers the filter (many other ways exist too)
@Order(-10)     // the lowest number runs first
@WebFilter(url) // if you wanted to limit the filter to certain urls
public class FooFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain sfc) throws IOException, ServletException {
        // run the next filter in the chain
        sfc.doFilter(req, res);
    }
}

// Can also do this
@Configuration
public class Whatever {

    @Bean
    public FilterRegistrationBean<FooFilter> fooFilter(){
        FilterRegistrationBean<RequestResponseLoggingFilter> regBean = new FilterRegistrationBean<>();

        regBean.setFilter(new FooFilter());
        regBean.addUrlPatterns("/users/*"); // for certain urls only
        regBean.setOrder(2);
        return regBean;    
    }
}
```
