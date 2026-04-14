package com.auth_service.core.application.usecase.impl;

import com.auth_service.adapters.in.web.dto.response.ResponseDTO;
import com.auth_service.core.application.usecase.DeleteUserUseCase;
import com.auth_service.core.domain.User;
import com.auth_service.core.domain.ports.out.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserRepository userRepository;

    public DeleteUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseDTO execute(String username) {

        Optional<User> user = userRepository.findUserByUsername(username);

        userRepository.deleteById(user.get().getId());

        return new ResponseDTO(
                200,
                "Usuário "+username+", deletado com sucesso!",
                LocalDateTime.now(),
                null
        );
    }
}
