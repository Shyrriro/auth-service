package com.auth_service.config.startup;

import com.auth_service.core.domain.User;
import com.auth_service.core.domain.enums.Role;
import com.auth_service.core.domain.ports.out.PasswordHasher;
import com.auth_service.core.domain.ports.out.UserRepository;
import com.auth_service.core.domain.vo.Email;
import com.auth_service.core.domain.vo.Password;
import com.auth_service.core.domain.vo.Username;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository repository;
    private final PasswordHasher passwordHasher;

    public AdminSeeder(UserRepository repository, PasswordHasher passwordHasher) {
        this.repository = repository;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public void run(String... args) {
        if (repository.existsByEmail("admin@api.com")) return;

        User admin = new User(
                null,
                new Username("admin"),
                new Email("admin@api.com"),
                new Password(Password.create("P@ssw0rd", passwordHasher).getValue()),
                Role.ADMIN
        );

        repository.save(admin);
    }
}