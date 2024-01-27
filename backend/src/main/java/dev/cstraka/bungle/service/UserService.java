package dev.cstraka.bungle.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.cstraka.bungle.controller.UserController.UserDto;
import dev.cstraka.bungle.model.User;
import dev.cstraka.bungle.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordService passwordService;

    public UserDto createUser(UserDto userDto) {
        String hashedPassword = passwordService.hashPassword(userDto.password());
        User user = new User(userDto.username(), hashedPassword, new ArrayList<String>());
        userRepository.save(user);
        return new UserDto(userDto.username(), null);
    }

    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.delete(user);
        }
    }
}
