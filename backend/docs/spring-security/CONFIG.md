# Configuration 

### AbstractHttpConfigurer 

AbstractHttpConfigurer is a helper class for customizing Spring Security's HTTP security configuration. 
It's used within HttpSecurity configuration to add or modify security features.

### Multiple SFC's 

Spring will autowire beans by type, the name doesn't matter.
The proxy

```java
public class SecurityConfig {
    @Bean
    public SecurityFilterChain foo(HttpSecurity http) throws Exception {
        return http 
                .requestMatchers()
                .build();
    }

    @Bean
    public SecurityFilterChain bar(HttpSecurity http) throws Exception {
        return http 
                .requestMatchers()
                .build();
    }
}
```

### JWT application.yaml 

If I was doing some JWT stuff...

```yml
# https://acte.ltd/utils/randomkeygen/ for the 256 bit encryption key
# https://magictool.ai/tool/text-to-hex-converter/ to convert to hex
 application:
   security:
     jwt:
       secret-key: 356861783554485748454846664e4251784166396a424a566247686155617830
       expiration: 86400000 # milliseconds in a day
       refresh-token:
         expiration: 604800000 # milliseconds in 7 days for dev
```
