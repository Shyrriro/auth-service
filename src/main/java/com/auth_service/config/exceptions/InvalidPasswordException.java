package com.auth_service.config.exceptions;

import org.jspecify.annotations.Nullable;
import org.springframework.dao.DataIntegrityViolationException;

public class InvalidPasswordException extends DataIntegrityViolationException {

    public InvalidPasswordException(@Nullable String msg) {
        super(msg);
    }

    public InvalidPasswordException(@Nullable String msg, @Nullable Throwable cause) {
        super(msg, cause);
    }
}
