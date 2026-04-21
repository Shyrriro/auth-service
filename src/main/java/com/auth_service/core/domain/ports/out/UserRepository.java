package com.auth_service.core.domain.ports.out;

import com.auth_service.core.domain.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {

    User save(User user);

    Optional<User> findUserByUsername(String username);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    void deleteById(UUID id);
}
