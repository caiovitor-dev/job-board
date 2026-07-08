package com.caio.job_board.dto;

import java.time.LocalDateTime;

public record EnterpriseResponseDTO(
        String name,
        String cnpj,
        String email,
        String description,
        LocalDateTime dateCreation,
        LocalDateTime dateUpdate
) {
}
