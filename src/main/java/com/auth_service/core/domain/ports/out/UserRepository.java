package com.auth_service.core.domain.ports.out;

import com.auth_service.core.domain.User;

public interface UserRepository {
    User save(User user);
}
