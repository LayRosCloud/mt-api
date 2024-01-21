package com.trans.api.mapper;

import com.trans.api.dto.stockmaterial.StockMaterialResponseDto;
import com.trans.api.entity.StockMaterialEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {StockMapper.class, MaterialMapper.class})
public interface StockMaterialMapper {
    List<StockMaterialResponseDto> toListDto(List<StockMaterialEntity> entities);
    StockMaterialResponseDto toDto(StockMaterialEntity entity);
}
