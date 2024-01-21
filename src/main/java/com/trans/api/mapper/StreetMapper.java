package com.trans.api.mapper;

import com.trans.api.dto.street.StreetResponseDto;
import com.trans.api.entity.StreetEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StreetMapper {
    List<StreetResponseDto> toListDto(List<StreetEntity> entities);
    StreetResponseDto toDto(StreetEntity entity);
}
