package com.auth_service.adapters.in.web.controller;

import com.auth_service.adapters.in.web.dto.request.UserRequestDTO;
import com.auth_service.adapters.in.web.dto.request.UserUpdateDTO;
import com.auth_service.core.application.usecase.impl.CreateUserUseCaseImpl;
import com.auth_service.core.application.usecase.impl.DeleteUserUseCaseImpl;
import com.auth_service.core.application.usecase.impl.GetUserByUsernameUseCaseImpl;
import com.auth_service.core.application.usecase.impl.UpdateUserUseCaseImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
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


    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequestDTO userRequestDTO) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createUser.execute(userRequestDTO));
    }

    @GetMapping("/{username}")
    private ResponseEntity<?> getUser(@PathVariable String username){
        return ResponseEntity.status(HttpStatus.OK).body(getUserByUsername.execute(username));
    }

    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@PathVariable String username, @RequestBody UserUpdateDTO userUpdateDTO) {
            return ResponseEntity.status(HttpStatus.OK).body(updateUser.execute(username, userUpdateDTO));
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username) {
        return ResponseEntity.status(HttpStatus.OK).body(deleteUser.execute(username));
    }
}
