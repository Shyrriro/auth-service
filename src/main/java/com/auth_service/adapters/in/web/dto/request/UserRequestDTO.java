package com.auth_service.adapters.in.web.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(
        @Size(min = 3, max = 20, message = "O nome de usuário deve ter entre 3 e 20 caracteres.")
        @NotBlank(message = "Nome de usuário é obrigatório")
        String username,
        @Email(message = "Email inválido", regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
        @NotBlank(message = "Email é obrigatório")
        String email,
        @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres.")
        @NotBlank(message = "Password é obrigatório")
        String password,
        String role) {
}
