package com.auth_service.core.domain;

import com.auth_service.core.domain.enums.Role;
import com.auth_service.core.domain.vo.Email;
import com.auth_service.core.domain.vo.Password;
import com.auth_service.core.domain.vo.Username;

import java.util.UUID;

public class User {

    private final UUID id;
    private final Username username;
    private final Email email;
    private final Password password;
    private final Role role;

    public User(UUID id, Username username, Email email, Password password, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public Username getUsername() {
        return username;
    }

    public Email getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }
}
