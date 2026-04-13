package com.auth_service.adapters.out.persistence;

import com.auth_service.adapters.out.persistence.Entity.UserEntity;
import com.auth_service.adapters.out.persistence.jpa.JpaUserRepository;
import com.auth_service.core.domain.User;
import com.auth_service.core.domain.enums.Role;
import com.auth_service.core.domain.ports.out.UserRepository;
import com.auth_service.core.domain.vo.Email;
import com.auth_service.core.domain.vo.Password;
import com.auth_service.core.domain.vo.Username;

public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User save(User user) {
        UserEntity newUser = new UserEntity(
                user.getId(),
                user.getUsername().getValue(),
                user.getEmail().getValue(),
                user.getPassword().getValue(),
                user.getRole().name()
                );

        var saved = jpaUserRepository.save(newUser);
        return new User(
                saved.getId(),
                new Username(saved.getUsername()),
                new Email(saved.getEmail()),
                new Password(saved.getPassword()),
                Role.valueOf(saved.getRole())
        );
    }

    @Override
    public boolean existsByUsername(Username username) {
        return jpaUserRepository.existsByUsername(username.getValue());
    }

    @Override
    public boolean existsByEmail(Email email) {
        return jpaUserRepository.existsByEmail(email.getValue());
    }
}
