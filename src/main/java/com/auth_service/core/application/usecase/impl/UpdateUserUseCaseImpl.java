package com.auth_service.core.application.usecase.impl;

import com.auth_service.adapters.in.web.dto.request.UserUpdateDTO;
import com.auth_service.adapters.in.web.dto.response.UserResponseDTO;
import com.auth_service.core.application.usecase.UpdateUserUseCase;
import com.auth_service.core.domain.User;
import com.auth_service.core.domain.ports.out.UserRepository;
import com.auth_service.core.domain.vo.Email;
import com.auth_service.core.domain.vo.Password;
import com.auth_service.core.domain.vo.Username;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public UpdateUserUseCaseImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public UserResponseDTO execute(String username, UserUpdateDTO userUpdateDTO) {

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new DataIntegrityViolationException("Usuário não encontrado")
        );

        if (userUpdateDTO.username() != null && !userUpdateDTO.username().isBlank()) {
            user = user.updateUsername(new Username(userUpdateDTO.username()));
        }
        if (userUpdateDTO.email() != null && !userUpdateDTO.email().isBlank()){
            user = user.updateEmail(new Email(userUpdateDTO.email()));
        }
        if (userUpdateDTO.password() != null && !userUpdateDTO.password().isBlank()) {
            user = user.changePassword(Password.create(userUpdateDTO.password(), encoder));
        }

        userRepository.save(user);

        return new UserResponseDTO(
                    user.getId(),
                    user.getUsername().getValue(),
                    user.getEmail().getValue(),
                    user.getRole().name()
        );
    }
}
