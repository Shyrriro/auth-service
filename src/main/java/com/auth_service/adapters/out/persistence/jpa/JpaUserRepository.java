package com.auth_service.adapters.out.persistence.jpa;

import com.auth_service.adapters.out.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByUsername(String value);
    boolean existsByEmail(String email);
    Optional<UserEntity> findUserByUsername(String username);
}
