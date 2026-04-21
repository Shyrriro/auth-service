package com.auth_service.adapters.in.web.controller;

import com.auth_service.adapters.in.web.dto.request.UserRequestDTO;
import com.auth_service.adapters.in.web.dto.request.UserUpdateDTO;
import com.auth_service.core.application.usecase.impl.CreateUserUseCaseImpl;
import com.auth_service.core.application.usecase.impl.DeleteUserUseCaseImpl;
import com.auth_service.core.application.usecase.impl.GetUserByUsernameUseCaseImpl;
import com.auth_service.core.application.usecase.impl.UpdateUserUseCaseImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class UserController {

    private final CreateUserUseCaseImpl createUser;
    private final GetUserByUsernameUseCaseImpl getUserByUsername;
    private final UpdateUserUseCaseImpl updateUser;
    private final DeleteUserUseCaseImpl deleteUser;

    public UserController(CreateUserUseCaseImpl createUser, GetUserByUsernameUseCaseImpl getUserByUsername, UpdateUserUseCaseImpl updateUser, DeleteUserUseCaseImpl deleteUser) {
        this.createUser = createUser;
        this.getUserByUsername = getUserByUsername;
        this.updateUser = updateUser;
        this.deleteUser = deleteUser;
    }

    @PostMapping("auth/singup")
    public ResponseEntity<?> createUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createUser.execute(userRequestDTO));
    }

    @GetMapping("/search/{username}")
    public ResponseEntity<?> getUser(@PathVariable String username){
        return ResponseEntity.status(HttpStatus.OK).body(getUserByUsername.execute(username));
    }

    @PreAuthorize("#username == authentication.name")
    @PutMapping("/updateaccount/{username}")
    public ResponseEntity<?> updateUser(@PathVariable @Valid String username, @RequestBody UserUpdateDTO userUpdateDTO) {
            return ResponseEntity.status(HttpStatus.OK).body(updateUser.execute(username, userUpdateDTO));
    }

    @PreAuthorize("#username == authentication.name")
    @DeleteMapping("/deleteaccount/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable @Valid String username) {
        return ResponseEntity.status(HttpStatus.OK).body(deleteUser.execute(username));
    }
}
