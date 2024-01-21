package com.trans.api.service;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.stock.StockCreateRequestDto;
import com.trans.api.dto.stock.StockResponseDto;
import com.trans.api.dto.stock.StockUpdateRequestDto;

import java.util.List;

public interface StockService {
    List<StockResponseDto> findAll();
    StockResponseDto findById(Short id);
    StockResponseDto create(StockCreateRequestDto dto);
    StockResponseDto update(StockUpdateRequestDto dto);
    AckDto delete(Short id);
}
