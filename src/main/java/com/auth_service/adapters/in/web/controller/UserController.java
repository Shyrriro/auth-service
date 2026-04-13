package com.auth_service.adapters.in.web.controller;

import com.auth_service.adapters.in.web.dto.request.UserRequestDTO;
import com.auth_service.core.application.usecase.impl.CreateUserUseCaseImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final CreateUserUseCaseImpl createUserService;

    public UserController(CreateUserUseCaseImpl createUserService) {
        this.createUserService = createUserService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO userRequestDTO) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createUserService.execute(userRequestDTO));
    }
}
