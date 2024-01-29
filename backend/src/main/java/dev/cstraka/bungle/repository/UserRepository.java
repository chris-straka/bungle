package dev.cstraka.bungle.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.cstraka.bungle.model.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
