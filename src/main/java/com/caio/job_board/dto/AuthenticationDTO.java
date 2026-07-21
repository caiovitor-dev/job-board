package com.caio.job_board.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthenticationDTO(
        @NotBlank(message = "Campo não pode está vazio" ) String email,
        @NotBlank(message = "Campo não pode está vazio" ) String password
) {
}
