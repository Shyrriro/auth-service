package com.auth_service.adapters.in.web.dto.request;

public record UserUpdateDTO(
        String username,
        String email,
        String password
) {}