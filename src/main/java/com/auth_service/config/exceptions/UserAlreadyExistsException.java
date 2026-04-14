package com.auth_service.config.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
public class UserAlreadyExistsException extends DataIntegrityViolationException {

    public UserAlreadyExistsException(String field, String value) {
        super("Já existe um usuário com este " + field + " " + value);
    }
}
