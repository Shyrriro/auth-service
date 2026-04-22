package com.auth_service.core.application.usecase.impl;

import com.auth_service.adapters.in.web.dto.response.ResponseDTO;
import com.auth_service.core.application.usecase.DeleteUserUseCase;
import com.auth_service.core.domain.User;
import com.auth_service.core.domain.ports.out.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;

public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    private final UserRepository userRepository;

    public DeleteUserUseCaseImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ResponseDTO execute(String username) {

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado")
        );

        userRepository.deleteById(user.getId());

        return new ResponseDTO(
                200,
                "Ok",
                LocalDateTime.now(),
                "Usuário "+username+", deletado com sucesso!"
        );
    }
}
