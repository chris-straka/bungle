# Filters

The way you register filters in Spring Security is different than vanilla spring.
You want to add the filters to your SecurityFilterChain (SFC), not the embedded container.
This means you want to avoid @Component, @Bean, @Configuration, on the filters.

```java
public class FooFilter extends Filter {
    void doFilter() {}
}

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http 
                .csrf()
                .sessionManagement()
                .authorizeHttpRequests()
                .addFilter(new FooFilter())
                // org.springframework.security.web might have insight on what filters exist
                .addFilterBefore(new FooFilter(), CsrfFilter.class)
                .oauth2ResourceServer()
                .exceptionHandling()
                .build();
    }
}
```
