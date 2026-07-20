package com.caio.job_board.dto;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public record ValidationErroResponseDTO(
        int status,
        String message,
        LocalDateTime timestamp,
        HashMap<String,String> errors
) {
}
