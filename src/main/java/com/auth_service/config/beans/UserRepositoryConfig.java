package com.auth_service.config.beans;

import com.auth_service.adapters.out.persistence.UserRepositoryImpl;
import com.auth_service.adapters.out.persistence.jpa.JpaUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserRepositoryConfig {

    @Bean
    UserRepositoryImpl userRepositoryImpl(JpaUserRepository jpaUserRepository){
        return new UserRepositoryImpl(jpaUserRepository);
    }
}
