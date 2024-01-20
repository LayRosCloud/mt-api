package com.trans.api.mapper;

import com.trans.api.dto.role.RoleResponseDto;
import com.trans.api.entity.RoleEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    List<RoleResponseDto> toListDto(List<RoleEntity> roles);
    RoleResponseDto toDto(RoleEntity role);
}
