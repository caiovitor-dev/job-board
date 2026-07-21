package com.caio.job_board.dto;

import jakarta.validation.constraints.NotBlank;

public record RoleCreateDTO(
        @NotBlank(message = "Campo não pode está vazio" )   String name
) {
}
