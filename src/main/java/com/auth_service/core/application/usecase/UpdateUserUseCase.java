package com.auth_service.core.application.usecase;

import com.auth_service.adapters.in.web.dto.request.UserUpdateDTO;
import com.auth_service.adapters.in.web.dto.response.UserResponseDTO;

public interface UpdateUserUseCase {
    UserResponseDTO execute(String username, UserUpdateDTO userUpdateDTO);
}
