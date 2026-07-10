package com.caio.job_board.mapper;

import com.caio.job_board.dto.EnterpriseCreateDTO;
import com.caio.job_board.dto.EnterpriseResponseDTO;
import com.caio.job_board.dto.EnterpriseUpdateDTO;
import com.caio.job_board.entity.Enterprise;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnterpriseMapper {

    public Enterprise toEntity(EnterpriseCreateDTO dto);

    public EnterpriseResponseDTO toDTO(Enterprise enterprise);

    public Enterprise toEntity(EnterpriseUpdateDTO dto);
}
