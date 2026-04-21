package com.auth_service.core.domain.vo;

import com.auth_service.core.domain.ports.out.PasswordHasher;

import java.util.Objects;
import java.util.regex.Pattern;

public class Password {

    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    private static final Pattern PATTERN = Pattern.compile(PASSWORD_REGEX);

    private String value;

    public Password(String value) {
        this.value = value;
    }

    public static Password create(String rawPassword, PasswordHasher passwordHasher) {
        validate(rawPassword);
        String hashedPassword = passwordHasher.hasher(rawPassword);
        return new Password(hashedPassword);
    }

    private static void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Campo Password não pode ser em branco.");
        }

        if (!PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException(
                    "Password deve conter pelomenos 8 caracteres, incluindo letra maiúscula, minúscula, número, e caractere especial."
            );
        }
        if (value.length() > 255) {
            throw new IllegalArgumentException("Campo Password deve conter no máximo 255 caracteres.");
        }
        if (value.contains(" ")) {
            throw new IllegalArgumentException("Campo Password não pode conter espaços.");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Password password)) return false;
        return Objects.equals(value, password.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
