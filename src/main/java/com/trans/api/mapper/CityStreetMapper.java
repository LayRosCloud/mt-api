package com.trans.api.mapper;

import com.trans.api.dto.citystreet.CityStreetResponseDto;
import com.trans.api.entity.CityStreetEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CityMapper.class, StreetMapper.class})
public interface CityStreetMapper {
    List<CityStreetResponseDto> toListDto(List<CityStreetEntity> entities);

    CityStreetResponseDto toDto(CityStreetEntity entity);
}
