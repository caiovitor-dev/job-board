package com.caio.job_board.dto;

import jakarta.validation.constraints.NotBlank;

public record EnterprisePatchDTO (
        @NotBlank(message = "Campo não pode está vazio" )  String name,
        @NotBlank(message = "Campo não pode está vazio" )  String cnpj,
        @NotBlank(message = "Campo não pode está vazio" )  String email,
        @NotBlank(message = "Campo não pode está vazio" )  String description
) {
}
