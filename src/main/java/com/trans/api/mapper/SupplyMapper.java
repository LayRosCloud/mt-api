package com.trans.api.mapper;

import com.trans.api.dto.supply.SupplyResponseDto;
import com.trans.api.entity.SupplyEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CountryMapper.class, StockMaterialMapper.class})
public interface SupplyMapper {
    List<SupplyResponseDto> toListDto(List<SupplyEntity> entities);

    SupplyResponseDto toDto(SupplyEntity entity);
}
