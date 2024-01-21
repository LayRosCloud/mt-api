package com.trans.api.service;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.stockmaterial.StockMaterialCreateRequestDto;
import com.trans.api.dto.stockmaterial.StockMaterialResponseDto;
import com.trans.api.dto.stockmaterial.StockMaterialUpdateRequestDto;

import java.util.List;

public interface StockMaterialService {
    List<StockMaterialResponseDto> findAll(Short id);
    StockMaterialResponseDto findByStockIdAndMaterialId(Short stockId, Integer materialId);
    StockMaterialResponseDto create(StockMaterialCreateRequestDto dto);
    StockMaterialResponseDto update(StockMaterialUpdateRequestDto dto);
    AckDto delete(Integer id);
}
