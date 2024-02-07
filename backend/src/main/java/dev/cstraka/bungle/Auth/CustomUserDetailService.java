package dev.cstraka.bungle.Auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.cstraka.bungle.User.UserEntity;
import dev.cstraka.bungle.User.UserRepository;

/**
 * Read the UserDetailsService interface that this class implements
 * Spring doesn't know how to retrieve users from the DB for authentication
 * purposes
 * So this tells the authentication manager how to get users from the DB
 */
@Service("userDetailsService")
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserEntity user = userRepository.findByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(username);

        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities("USER").build();
    }
}
