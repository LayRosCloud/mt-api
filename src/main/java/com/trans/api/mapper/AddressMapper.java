package com.trans.api.mapper;

import com.trans.api.dto.address.AddressResponseDto;
import com.trans.api.entity.AddressEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CityStreetMapper.class})
public interface AddressMapper {
    List<AddressResponseDto> toListDto(List<AddressEntity> entities);
    AddressResponseDto toDto(AddressEntity entity);
}
