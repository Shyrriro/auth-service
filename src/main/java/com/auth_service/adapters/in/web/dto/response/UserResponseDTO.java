package com.auth_service.adapters.in.web.dto.response;

import java.util.UUID;

public record UserResponseDTO(UUID id, String username, String email, String role) {
}
