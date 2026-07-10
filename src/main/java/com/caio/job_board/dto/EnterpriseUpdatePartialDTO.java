package com.caio.job_board.dto;

public record EnterpriseUpdatePartialDTO (
        String name,
        String email,
        String description,
        String cnpj
){
}
