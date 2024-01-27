package com.trans.api.mapper;

import com.trans.api.dto.city.CityResponseDto;
import com.trans.api.entity.CityEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AreaMapper.class})
public interface CityMapper {
    List<CityResponseDto> toListDto(List<CityEntity> entities);
    CityResponseDto toDto(CityEntity entity);
}
