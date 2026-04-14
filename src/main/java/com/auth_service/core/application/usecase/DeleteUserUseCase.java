package com.auth_service.core.application.usecase;

import com.auth_service.adapters.in.web.dto.response.ResponseDTO;

public interface DeleteUserUseCase {
    ResponseDTO execute(String username);
}
