package dev.cstraka.bungle.user;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

import dev.cstraka.bungle.user.jpa.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
