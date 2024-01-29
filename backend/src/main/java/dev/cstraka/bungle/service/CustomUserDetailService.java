package dev.cstraka.bungle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.cstraka.bungle.model.UserEntity;
import dev.cstraka.bungle.repository.UserRepository;

/**
 * Read the UserDetailsService interface that this class implements
 * Spring doesn't know how to retrieve users from the DB for authentication purposes
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

        boolean enabled = !user.isAccountVerified();
        UserDetails userDetails = User.withUsername(user.getUsername())
                .password(user.getPassword())
                .disabled(enabled)
                .authorities("USER").build();
        return userDetails;
    }
}
