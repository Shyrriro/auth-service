package com.auth_service.adapters.out.persistence;

import com.auth_service.adapters.out.persistence.entity.UserEntity;
import com.auth_service.adapters.out.persistence.jpa.JpaUserRepository;
import com.auth_service.core.domain.User;
import com.auth_service.core.domain.enums.Role;
import com.auth_service.core.domain.ports.out.UserRepository;
import com.auth_service.core.domain.vo.Email;
import com.auth_service.core.domain.vo.Password;
import com.auth_service.core.domain.vo.Username;

import java.util.Optional;
import java.util.UUID;

public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User save(User user) {

        UserEntity userEntity;

        if (user.getId() != null) {
            userEntity = jpaUserRepository.findById(user.getId())
                    .orElseThrow();
        } else {
            userEntity = new UserEntity();
        }

        userEntity.setUsername(user.getUsername().getValue());
        userEntity.setEmail(user.getEmail().getValue());
        userEntity.setPassword(user.getPassword().getValue());
        userEntity.setRole(user.getRole().name());

        var saved = jpaUserRepository.save(userEntity);

        return new User(
                saved.getId(),
                new Username(saved.getUsername()),
                new Email(saved.getEmail()),
                new Password(saved.getPassword()),
                Role.valueOf(saved.getRole())
        );
    }

    @Override
    public Optional<User> findUserByUsername(String username) {

        return jpaUserRepository.findUserByUsername(username)
                .map(userEntity -> new User(
                        userEntity.getId(),
                        new Username(userEntity.getUsername()),
                        new Email(userEntity.getEmail()),
                        new Password(userEntity.getPassword()),
                        Role.valueOf(userEntity.getRole())
                ));
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpaUserRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }

    @Override
    public void deleteById(UUID id) {
        jpaUserRepository.deleteById(id);
    }
}
