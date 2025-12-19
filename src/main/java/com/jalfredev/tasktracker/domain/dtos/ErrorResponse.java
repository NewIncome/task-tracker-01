package com.jalfredev.tasktracker.domain.dtos;

public record ErrorResponse(
    int status,
    String message,
    String details
) {
}
