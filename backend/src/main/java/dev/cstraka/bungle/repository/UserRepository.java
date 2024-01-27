package dev.cstraka.bungle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.cstraka.bungle.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
