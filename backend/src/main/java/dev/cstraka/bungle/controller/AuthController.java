package dev.cstraka.bungle.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/session")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken
                .unauthenticated(loginRequest.username, loginRequest.password);
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);

        SecurityContextHolder.getContext().setAuthentication(authenticationResponse);

        return ResponseEntity.ok().body(loginRequest.username);
    }

    @DeleteMapping("/session")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext();
        request.getSession().invalidate(); // Invalidate the session

        return ResponseEntity.noContent().build();
    }

    // record -> java 16 immutable data class
    public record LoginRequest(String username, String password) {
    }
}
