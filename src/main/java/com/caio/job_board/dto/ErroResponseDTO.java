package com.caio.job_board.dto;

import org.springframework.http.HttpStatus;

public record ErroResponseDTO(
        int statusCode,
        String message
) {
}
