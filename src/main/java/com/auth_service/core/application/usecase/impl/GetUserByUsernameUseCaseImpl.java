package com.auth_service.core.application.usecase.impl;

import com.auth_service.adapters.in.web.dto.response.UserResponseDTO;
import com.auth_service.core.application.usecase.GetUserByUsernameUseCase;
import com.auth_service.core.domain.User;
import com.auth_service.core.domain.ports.out.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class GetUserByUsernameUseCaseImpl implements GetUserByUsernameUseCase {

    private final UserRepository userRepository;

    public GetUserByUsernameUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO execute(String username) {

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado")
        );

        return new UserResponseDTO(
            user.getId(),
            user.getUsername().getValue(),
            user.getEmail().getValue(),
            user.getRole().name()
        );
    }
}
