package com.auth_service.core.application.usecase;

import com.auth_service.adapters.in.web.dto.response.UserResponseDTO;

public interface GetUserByUsernameUseCase {
    UserResponseDTO execute(String username);
}
