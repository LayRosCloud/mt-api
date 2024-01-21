package com.trans.api.mapper;

import com.trans.api.dto.user.UserResponseDto;
import com.trans.api.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UserMapper {
    List<UserResponseDto> toListDto(List<UserEntity> entities);
    UserResponseDto toDto(UserEntity entity);

}
