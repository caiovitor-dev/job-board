package com.caio.job_board.mapper;

import com.caio.job_board.dto.UserCreateDTO;
import com.caio.job_board.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public User toEntity(UserCreateDTO dto);
}
