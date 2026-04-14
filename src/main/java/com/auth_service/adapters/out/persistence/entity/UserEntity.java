package com.auth_service.adapters.out.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false, length = 20)
    private String username;

    @Size(min = 6, max = 254, message = "Email muito longo")
    @Column(unique = true, nullable = false, length = 254)
    private String email;

    @Size(min = 8, max = 255, message = "Senha muito longa")
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;
}
