package com.trans.api.mapper;

import com.trans.api.dto.country.CountryResponseDto;
import com.trans.api.entity.CountryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {
    List<CountryResponseDto> toListDto(List<CountryEntity> entities);
    CountryResponseDto toDto(CountryEntity entity);
}
