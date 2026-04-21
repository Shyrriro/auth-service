package com.auth_service.core.application.usecase.impl;

import com.auth_service.adapters.in.web.dto.request.UserUpdateDTO;
import com.auth_service.adapters.in.web.dto.response.UserResponseDTO;
import com.auth_service.core.application.usecase.UpdateUserUseCase;
import com.auth_service.core.domain.User;
import com.auth_service.core.domain.ports.out.PasswordHasher;
import com.auth_service.core.domain.ports.out.UserRepository;
import com.auth_service.core.domain.vo.Email;
import com.auth_service.core.domain.vo.Password;
import com.auth_service.core.domain.vo.Username;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UpdateUserUseCaseImpl implements UpdateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordHasher passwordHasher;

    public UpdateUserUseCaseImpl(UserRepository userRepository, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public UserResponseDTO execute(String username, UserUpdateDTO userUpdateDTO) {

        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado")
        );

        if (userUpdateDTO.username() != null && !userUpdateDTO.username().isBlank()) {
            user = user.updateUsername(new Username(userUpdateDTO.username()));
        }
        if (userUpdateDTO.email() != null && !userUpdateDTO.email().isBlank()){
            user = user.updateEmail(new Email(userUpdateDTO.email()));
        }
        if (userUpdateDTO.newPassword() != null && !userUpdateDTO.newPassword().isBlank() && userUpdateDTO.passwordConfirm() != null && !userUpdateDTO.passwordConfirm().isBlank()) {
            if(userUpdateDTO.newPassword().equals(userUpdateDTO.passwordConfirm())){
            user = user.changePassword(Password.create(userUpdateDTO.newPassword(), passwordHasher));
            } else {
                throw new IllegalArgumentException("Senhas não coincidem.");
            }
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
