package com.caio.job_board.dto;

import jakarta.validation.constraints.NotBlank;

public record UserCreateDTO(
        @NotBlank(message = "Campo não pode está vazio" ) String cpf,
        @NotBlank(message = "Campo não pode está vazio" ) String password,
        @NotBlank(message = "Campo não pode está vazio" ) String email,
        @NotBlank(message = "Campo não pode está vazio" ) String name
) {
}
