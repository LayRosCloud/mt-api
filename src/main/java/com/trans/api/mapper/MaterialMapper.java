package com.trans.api.mapper;

import com.trans.api.dto.country.CountryResponseDto;
import com.trans.api.dto.material.MaterialResponseDto;
import com.trans.api.entity.CountryEntity;
import com.trans.api.entity.MaterialEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UnitMapper.class})
public interface MaterialMapper {
    List<MaterialResponseDto> toListDto(List<MaterialEntity> entities);
    MaterialResponseDto toDto(MaterialEntity entity);
}
