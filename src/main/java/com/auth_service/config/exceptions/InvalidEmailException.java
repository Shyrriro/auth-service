package com.auth_service.config.exceptions;

import org.jspecify.annotations.Nullable;
import org.springframework.dao.DataIntegrityViolationException;

public class InvalidEmailException extends DataIntegrityViolationException {

    public InvalidEmailException(@Nullable String msg) {
        super(msg);
    }

    public InvalidEmailException(@Nullable String msg, @Nullable Throwable cause) {
        super(msg, cause);
    }
}
