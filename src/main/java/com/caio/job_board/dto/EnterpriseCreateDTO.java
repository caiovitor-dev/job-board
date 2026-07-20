package com.caio.job_board.dto;

import jakarta.validation.constraints.NotBlank;

public record EnterpriseCreateDTO(
       @NotBlank(message = "Campo não pode está vazio" ) String name,
       @NotBlank  String cnpj,
       @NotBlank String email,
       @NotBlank String description
) {
}
