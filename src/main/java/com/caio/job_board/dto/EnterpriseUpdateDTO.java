package com.caio.job_board.dto;

public record EnterpriseUpdateDTO (
        String name,
        String cnpj,
        String email,
        String description
){
}
