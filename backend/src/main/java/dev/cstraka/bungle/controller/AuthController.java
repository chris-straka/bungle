package dev.cstraka.bungle.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.cstraka.bungle.dto.LoginRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest) {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken
                .unauthenticated(loginRequest.username, loginRequest.password);
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);

        return null;
    }

    // record -> java 16 immutable data class
    public record LoginRequest(String username, String password) {}
}
