package com.caio.job_board.mapper;

import com.caio.job_board.dto.RoleCreateDTO;
import com.caio.job_board.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    public Role toEntity(RoleCreateDTO dto);
}
