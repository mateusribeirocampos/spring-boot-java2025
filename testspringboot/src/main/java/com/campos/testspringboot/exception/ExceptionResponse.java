package com.campos.testspringboot.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record ExceptionResponse(
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime timestamp,
        String message,
        String details,
        String path
) {
    public static ExceptionResponse of(String message, String details, String path) {
        return new ExceptionResponse(LocalDateTime.now(), message, details, path);
    }
}
