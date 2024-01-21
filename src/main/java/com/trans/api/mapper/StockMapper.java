package com.trans.api.mapper;

import com.trans.api.dto.stock.StockResponseDto;
import com.trans.api.entity.StockEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface StockMapper {
    List<StockResponseDto> toListDto(List<StockEntity> entities);
    StockResponseDto toDto(StockEntity entity);
}
