package com.example.backend.exception;

import java.time.LocalDateTime;

public record ErrorResponseDTO(

        LocalDateTime timestamp,

        Integer status,

        String error,

        String message,

        String path

) {
}