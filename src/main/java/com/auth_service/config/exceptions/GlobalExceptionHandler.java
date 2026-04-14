package com.auth_service.config.exceptions;

import com.auth_service.adapters.in.web.dto.response.ErrorResponseDTO;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDTO> handleValidationErrors(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(
                new ErrorResponseDTO(
                        400,
                        "Bad Request",
                        "Erro de validação",
                        LocalDateTime.now(),
                        errors
                )
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleConstraintViolation(ConstraintViolationException ex) {

        return ResponseEntity.badRequest().body(
                new ErrorResponseDTO(
                        400,
                        "Bad Request",
                        "Violação de constraints",
                        LocalDateTime.now(),
                        ex.getMessage()
                )
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleRuntimeException(RuntimeException ex) {

        return ResponseEntity.badRequest().body(
                new ErrorResponseDTO(
                        400,
                        "Bad Request",
                        "Erro de execução",
                        LocalDateTime.now(),
                        ex.getMessage()
                )
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDTO> handleDataIntegrity(DataIntegrityViolationException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ErrorResponseDTO(
                        409,
                        "Conflict",
                        "Violação de integridade de dados",
                        LocalDateTime.now(),
                        ex.getMessage()
                )
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleIllegalArgument(IllegalArgumentException ex) {
        return ResponseEntity.badRequest()
                .body(new ErrorResponseDTO(
                        400,
                        "Bad Request",
                        ex.getMessage(),
                        LocalDateTime.now(),
                        null
                ));
    }

    @ExceptionHandler(UserPrincipalNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserNotFound(UserPrincipalNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponseDTO(
                        HttpStatus.NOT_FOUND.value(),
                        "Not Found",
                        ex.getMessage(),
                        LocalDateTime.now(),
                        null
                ));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDTO> handleAccessDenied(AccessDeniedException ex) {

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ErrorResponseDTO(
                        403,
                        "Forbidden",
                        "Acesso negado",
                        LocalDateTime.now(),
                        null
                )
        );
    }

    @ExceptionHandler(org.springframework.security.core.AuthenticationException.class)
    public ResponseEntity<ErrorResponseDTO> handleAuthenticationException(Exception ex) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ErrorResponseDTO(
                        401,
                        "Unauthorized",
                        "Não autenticado",
                        LocalDateTime.now(),
                        null
                )
        );
    }

    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO> handleJsonError(Exception ex) {

        return ResponseEntity.badRequest().body(
                new ErrorResponseDTO(
                        400,
                        "Bad Request",
                        "JSON inválido ou mal formatado",
                        LocalDateTime.now(),
                        null
                )
        );
    }

    @ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponseDTO> handleMethodNotAllowed(Exception ex) {

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(
                new ErrorResponseDTO(
                        405,
                        "Method Not Allowed",
                        ex.getMessage(),
                        LocalDateTime.now(),
                        null
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGeneric(Exception ex) {

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ErrorResponseDTO(
                        500,
                        "Internal Server Error",
                        "Erro inesperado",
                        LocalDateTime.now(),
                        ex.getMessage()
                )
        );
    }
}