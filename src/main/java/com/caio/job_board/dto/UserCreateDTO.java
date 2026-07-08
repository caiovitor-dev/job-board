package com.caio.job_board.dto;

public record UserCreateDTO(
        String cpf,
        String password,
        String email,
        String name
) {
}
