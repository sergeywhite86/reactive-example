package ru.sergey_white.reactiveexample.model.dto;


import java.time.Instant;

public record AppError(
        int status,
        String error,
        String message,
        Instant timestamp
) {
    public AppError(int status, String error, String message) {
        this(status, error, message, Instant.now());
    }
}
