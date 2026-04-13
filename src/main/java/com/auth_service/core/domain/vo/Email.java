package com.auth_service.core.domain.vo;

import java.util.Objects;
import java.util.regex.Pattern;

public class Email {

    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

    private static final Pattern PATTERN = Pattern.compile(EMAIL_REGEX);

    private final String value;

    public Email(String value) {
        validate(value);
        this.value = value.toLowerCase();
    }

    private void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Campo Email não pode ser em branco");
        }

        if (!PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Email inválido.");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Email email)) return false;
        return Objects.equals(value, email.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}