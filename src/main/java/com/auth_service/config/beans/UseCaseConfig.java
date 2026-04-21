package com.auth_service.config.beans;

import com.auth_service.core.application.usecase.impl.CreateUserUseCaseImpl;
import com.auth_service.core.application.usecase.impl.DeleteUserUseCaseImpl;
import com.auth_service.core.application.usecase.impl.GetUserByUsernameUseCaseImpl;
import com.auth_service.core.application.usecase.impl.UpdateUserUseCaseImpl;
import com.auth_service.core.domain.ports.out.PasswordHasher;
import com.auth_service.core.domain.ports.out.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    CreateUserUseCaseImpl createUserUseCase(UserRepository repository, PasswordHasher passwordHasher) {
        return new CreateUserUseCaseImpl(repository, passwordHasher);
    }

    @Bean
    DeleteUserUseCaseImpl deleteUserUseCase(UserRepository repository) {
        return new DeleteUserUseCaseImpl(repository);
    }

    @Bean
    UpdateUserUseCaseImpl updateUserUseCase(UserRepository repository, PasswordHasher passwordHasher) {
        return new UpdateUserUseCaseImpl(repository, passwordHasher);
    }

    @Bean
    GetUserByUsernameUseCaseImpl getUserByUsernameUseCase(UserRepository repository) {
        return new GetUserByUsernameUseCaseImpl(repository);
    }
}
