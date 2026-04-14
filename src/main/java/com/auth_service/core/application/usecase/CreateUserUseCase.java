package com.auth_service.core.application.usecase;

import com.auth_service.adapters.in.web.dto.request.UserRequestDTO;
import com.auth_service.adapters.in.web.dto.response.UserResponseDTO;

public interface CreateUserUseCase {
        UserResponseDTO execute(UserRequestDTO userRequestDTO);
}
