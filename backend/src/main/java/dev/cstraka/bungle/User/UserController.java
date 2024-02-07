package dev.cstraka.bungle.User;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<UserDto> signup(@RequestBody UserDto userDto) {
        UserDto createdUser = userService.createUser(userDto);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // @PutMapping("/user/{id}")
    // public SomeEnityData putMethodName(@PathVariable String id, @RequestBody SomeEnityData entity) {
        
    //     return entity;
    // }

    @DeleteMapping("/user")
    public ResponseEntity<Void> deleteMe(Authentication authentication) {
        String username = authentication.getName();
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

    public record UserDto(String username, String password) {
    }
}
