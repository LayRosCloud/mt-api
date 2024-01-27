package com.trans.api.mapper;

import com.trans.api.dto.region.RegionResponseDto;
import com.trans.api.entity.RegionEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CountryMapper.class})
public interface RegionMapper {
    List<RegionResponseDto> toListDto(List<RegionEntity> entities);
    RegionResponseDto toDto(RegionEntity entity);
}
