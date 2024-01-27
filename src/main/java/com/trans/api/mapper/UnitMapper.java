package com.trans.api.mapper;

import com.trans.api.dto.unit.UnitResponseDto;
import com.trans.api.entity.UnitEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UnitMapper {
    List<UnitResponseDto> toListDto(List<UnitEntity> entities);

    UnitResponseDto toDto(UnitEntity entity);
}
