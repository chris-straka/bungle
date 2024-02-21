package dev.cstraka.bungle.user;

import org.springframework.stereotype.Service;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import dev.cstraka.bungle.user.UserController.ChangePasswordRequest;
import dev.cstraka.bungle.user.UserController.UserDto;

import java.security.Principal;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto createUser(UserDto userDto) {
        User user = new User(userDto.username(), userDto.password(), userDto.userRole());
        userRepository.save(user);

        return new UserDto(userDto.username(), null, null);
    }

    public void deleteUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        if (user != null)
            userRepository.delete(user);
    }

    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.currentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.newPassword().equals(request.confirmationPassword())) {
            throw new IllegalStateException("Passwords are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.newPassword()));

        // save the new password
        userRepository.save(user);
    }
}
