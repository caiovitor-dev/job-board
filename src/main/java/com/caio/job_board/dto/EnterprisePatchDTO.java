package com.caio.job_board.dto;

public record EnterprisePatchDTO (
        String name,
        String cnpj,
        String email,
        String description
) {
}
