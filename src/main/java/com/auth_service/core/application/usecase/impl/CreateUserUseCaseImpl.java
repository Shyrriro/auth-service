package com.auth_service.core.application.usecase.impl;

import com.auth_service.adapters.in.web.dto.request.UserRequestDTO;
import com.auth_service.adapters.in.web.dto.response.UserResponseDTO;
import com.auth_service.config.exceptions.UserAlreadyExistsException;
import com.auth_service.core.domain.User;
import com.auth_service.core.application.usecase.CreateUserUseCase;
import com.auth_service.core.domain.enums.Role;
import com.auth_service.core.domain.ports.out.UserRepository;
import com.auth_service.core.domain.vo.Email;
import com.auth_service.core.domain.vo.Password;
import com.auth_service.core.domain.vo.Username;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public CreateUserUseCaseImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserResponseDTO execute(UserRequestDTO userRequestDTO) {

        Password password = Password.create(userRequestDTO.password(), passwordEncoder);

        User user = new User(
                null,
                new Username(userRequestDTO.username()),
                new Email(userRequestDTO.email()),
                password,
                Role.USER
        );

        if(userRepository.existsByUsername(user.getUsername().getValue())) {
            throw new UserAlreadyExistsException("nome", userRequestDTO.username());
        } else if (userRepository.existsByEmail(user.getEmail().getValue())) {
            throw new UserAlreadyExistsException("email", userRequestDTO.email());
        }

        var saved = userRepository.save(user);

        return new UserResponseDTO(
                saved.getId(),
                saved.getUsername().getValue(),
                saved.getEmail().getValue(),
                saved.getRole().name()
        );
    }
}
