package com.caio.job_board.mapper;

import com.caio.job_board.dto.EnterpriseCreateDTO;
import com.caio.job_board.dto.EnterprisePatchDTO;
import com.caio.job_board.dto.EnterpriseResponseDTO;
import com.caio.job_board.dto.EnterpriseUpdateDTO;
import com.caio.job_board.entity.Enterprise;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface EnterpriseMapper {

    public Enterprise toEntity(EnterpriseCreateDTO dto);

    public EnterpriseResponseDTO toDTO(Enterprise enterprise);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    public void updatePartial(EnterprisePatchDTO dto, @MappingTarget Enterprise enterprise);
}
