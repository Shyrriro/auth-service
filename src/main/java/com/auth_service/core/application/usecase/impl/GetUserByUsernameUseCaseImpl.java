package com.auth_service.core.application.usecase.impl;

import com.auth_service.adapters.in.web.dto.response.UserResponseDTO;
import com.auth_service.core.domain.ports.out.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;

public class GetUserByUsernameUseCaseImpl {

    private final UserRepository userRepository;

    public GetUserByUsernameUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO execute(String username) {

        var user = userRepository.findUserByUsername(username);

        if (user.isEmpty()) {
            throw new DataIntegrityViolationException("Usuário não encontrado");
        }

        return new UserResponseDTO(
            user.get().getId(),
            user.get().getUsername().getValue(),
            user.get().getEmail().getValue(),
            user.get().getRole().name()
        );
    }
}
