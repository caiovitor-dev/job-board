package com.caio.job_board.dto;

public record EnterpriseCreateDTO(
        String name,
        String cnpj,
        String email,
        String description
) {
}
