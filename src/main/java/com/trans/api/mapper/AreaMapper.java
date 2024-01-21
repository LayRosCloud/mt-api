package com.trans.api.mapper;

import com.trans.api.dto.area.AreaResponseDto;
import com.trans.api.entity.AreaEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RegionMapper.class})
public interface AreaMapper {
    List<AreaResponseDto> toListDto(List<AreaEntity> entities);
    AreaResponseDto toDto(AreaEntity entity);
}
