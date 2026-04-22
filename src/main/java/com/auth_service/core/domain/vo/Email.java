package com.auth_service.core.domain.vo;

import com.auth_service.config.exceptions.InvalidEmailException;

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
            throw new InvalidEmailException("Campo Email não pode ser em branco");
        }

        if (!PATTERN.matcher(value).matches()) {
            throw new InvalidEmailException("Email inválido.");
        }

        if (value.length() < 6 || value.length() > 254) {
            throw new InvalidEmailException("O email deve ter entre 6 e 254 caracteres.");
        }

        if (value.indexOf('@') > 64) {
            throw new InvalidEmailException("A parte local do email deve ter no máximo 64 caracteres.");
        }

        if (value.contains("..")) {
            throw new InvalidEmailException("O email não pode conter pontos consecutivos.");
        }

        if (value.startsWith(".") || value.endsWith(".")) {
            throw new InvalidEmailException("O email não pode começar ou terminar com um ponto.");
        }

        if (value.contains(" ")) {
            throw new IllegalArgumentException("O email não pode conter espaços.");
        }

        if (value.chars().filter(ch -> ch == '@').count() != 1) {
            throw new IllegalArgumentException("O email deve conter exatamente um caractere '@'.");
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