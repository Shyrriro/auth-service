package com.auth_service.core.domain.vo;

import java.util.Objects;
import java.util.regex.Pattern;

public class Username {

    private static final String USERNAME_REGEX =
            "^(?=.{3,20}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$";

    private static final Pattern PATTERN = Pattern.compile(USERNAME_REGEX);

    private final String value;

    public Username(String value) {
        validate(value);
        this.value = value.toLowerCase();
    }

    private void validate(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Campo de usuário não pode ser em branco");
        }
        if (!PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException(
                    "Nome de usuário deve conter no mínimo 3 letras e o máximo de 20 letras, sem espaço, não pode iniciar ou terminar com '.' or '_', e sem símbolos consecutivos"
            );
        }

        if (value.contains(" ")) {
            throw new IllegalArgumentException("Nome de usuário não pode conter espaços");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Username username)) return false;
        return Objects.equals(value, username.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
