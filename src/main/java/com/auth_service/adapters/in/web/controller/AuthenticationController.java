package com.auth_service.adapters.in.web.controller;

import com.auth_service.adapters.in.web.dto.request.AuthenticationDTO;
import com.auth_service.adapters.in.web.dto.response.LoginResponseDTO;
import com.auth_service.config.security.CustomUserDetails;
import com.auth_service.config.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthenticationDTO authenticationDTO) {

        var usernamePassword = new UsernamePasswordAuthenticationToken(authenticationDTO.username().toLowerCase(), authenticationDTO.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        var token = tokenService.generateToken(userDetails.getUser());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }
}

