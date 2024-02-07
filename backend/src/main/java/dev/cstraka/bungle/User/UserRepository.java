package dev.cstraka.bungle.User;

import org.springframework.data.repository.CrudRepository;

/**
 * Could've used JPA Repository
 * https://stackoverflow.com/questions/14014086
 * https://stackoverflow.com/a/20784007/13155357
 */
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByUsername(String username);
}
