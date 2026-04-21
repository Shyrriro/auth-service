package com.auth_service.adapters.in.web.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserUpdateDTO(
        @Size(min = 3, max = 20, message = "O nome de usuário deve ter entre 3 e 20 caracteres.")
        String username,
        @Size(min = 6, max = 254, message = "O email deve ter entre 6 e 254 caracteres.")
        @Email(message = "Email inválido", regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
        String email,
        @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres.")
        String newPassword,
        @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres.")
        String passwordConfirm
) {}