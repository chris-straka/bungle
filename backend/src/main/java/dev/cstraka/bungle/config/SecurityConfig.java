package dev.cstraka.bungle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


/**
 * 1. DelegatingFilterProxy -> Connects servlet lifecycle with spring application context
 * 2. FilterChainProxy -> Contains SecurityFilterChains (SFCs) and forwards requests to them
 * 3. SecurityFilterChain -> Has all the security filters I want to apply
 * 4. SecurityFilters -> middleware for authentication, authorization, CSRF, etc.
 * 
 * Customizer.withDefaults() is a no-op to mark my intentions
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/login", "/logout").permitAll()
                        .anyRequest().authenticated())
                .logout((logout) -> logout.permitAll());

        return http.build();
    }
}
