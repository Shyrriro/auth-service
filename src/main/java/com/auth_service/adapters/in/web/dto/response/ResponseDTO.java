package com.auth_service.adapters.in.web.dto.response;

import java.time.LocalDateTime;

public record ResponseDTO(
        int status,
        String message,
        LocalDateTime timestamp,
        Object details) {
}
