// package dev.cstraka.bungle.service;

// import java.util.ArrayList;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import dev.cstraka.bungle.controller.UserController.UserDto;
// import dev.cstraka.bungle.model.UserEntity;
// import dev.cstraka.bungle.repository.UserRepository;

// @Service
// public class UserService {
//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private PasswordService passwordService;

//     public UserDto createUser(UserDto userDto) {
//         String hashedPassword = passwordService.hashPassword(userDto.password());
//         UserEntity user = new UserEntity(userDto.username(), hashedPassword, new ArrayList<String>());
//         userRepository.save(user);
//         return new UserDto(userDto.username(), null);
//     }

//     public void deleteUser(String username) {
//         UserEntity user = userRepository.findByUsername(username);
//         if (user != null) {
//             userRepository.delete(user);
//         }
//     }
// }
