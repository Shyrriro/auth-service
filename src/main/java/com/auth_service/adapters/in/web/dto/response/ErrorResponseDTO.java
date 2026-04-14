package com.auth_service.adapters.in.web.dto.response;

import java.time.LocalDateTime;

public record ErrorResponseDTO(
        int status,
        String error,
        String message,
        LocalDateTime timestamp,
        Object details
) {}
