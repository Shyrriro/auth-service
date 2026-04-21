package com.auth_service.adapters.in.web.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
        @NotBlank(message = "Nome de usuário é obrigatório")
        String username,
        @NotBlank(message = "Password é obrigatório")
        String password) {

        @Override
        public String username() {
                return username.toLowerCase();
        }
}
