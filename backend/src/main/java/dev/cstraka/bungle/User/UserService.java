package dev.cstraka.bungle.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.cstraka.bungle.User.UserController.UserDto;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public UserDto createUser(UserDto userDto) {
        UserEntity user = new UserEntity(userDto.username(), userDto.password());
        userRepository.save(user);

        return new UserDto(userDto.username(), null);
    }

    public void deleteUser(String username) {
        UserEntity user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.delete(user);
        }
    }
}
