package com.auth_service.core.domain.ports.out;

public interface PasswordHasher {
    String hasher(String raw);
    boolean matches(String raw, String hashed);
}
