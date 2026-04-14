package com.auth_service.config.startup;

import com.auth_service.core.domain.User;
import com.auth_service.core.domain.enums.Role;
import com.auth_service.core.domain.ports.out.UserRepository;
import com.auth_service.core.domain.vo.Email;
import com.auth_service.core.domain.vo.Password;
import com.auth_service.core.domain.vo.Username;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository repository;
    private final PasswordEncoder encoder;

    public AdminSeeder(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) {
        if (repository.existsByEmail("admin@api.com")) return;

        User admin = new User(
                null,
                new Username("admin"),
                new Email("admin@api.com"),
                new Password(encoder.encode("123456!")),
                Role.ADMIN
        );

        repository.save(admin);
    }
}