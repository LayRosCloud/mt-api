package com.trans.api.service.impl;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.stockmaterial.StockMaterialCreateRequestDto;
import com.trans.api.dto.stockmaterial.StockMaterialResponseDto;
import com.trans.api.dto.stockmaterial.StockMaterialUpdateRequestDto;
import com.trans.api.entity.MaterialEntity;
import com.trans.api.entity.StockEntity;
import com.trans.api.entity.StockMaterialEntity;
import com.trans.api.mapper.StockMaterialMapper;
import com.trans.api.repository.MaterialRepository;
import com.trans.api.repository.StockMaterialRepository;
import com.trans.api.repository.StockRepository;
import com.trans.api.scripts.helpers.ThrowableHelper;
import com.trans.api.service.StockMaterialService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class StockMaterialServiceImpl implements StockMaterialService {

    MaterialRepository materialRepository;
    StockMaterialRepository stockMaterialRepository;
    StockRepository stockRepository;

    StockMaterialMapper mapper;

    @Override
    public List<StockMaterialResponseDto> findAll(Short stockId) {
        List<StockMaterialEntity> stocks = new ArrayList<>();

        if(stockId == 0){
            stocks.addAll(stockMaterialRepository.findAll());
        }else{
            stocks.addAll(stockMaterialRepository.findAllByStockId(stockId));
        }

        return mapper.toListDto(stocks);
    }

    @Override
    public StockMaterialResponseDto findByStockIdAndMaterialId(Short stockId, Integer materialId) {
        StockMaterialEntity stockMaterial = getStockMaterialOrThrowNotFoundException(stockId, materialId);
        return mapper.toDto(stockMaterial);
    }

    @Override
    @Transactional
    public StockMaterialResponseDto create(Short stockId, Integer materialId, StockMaterialCreateRequestDto dto) {

        MaterialEntity material = materialRepository.findById(materialId).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(stockId))
        );

        StockEntity stock = stockRepository.findById(stockId).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(materialId))
        );

        StockMaterialEntity entity = StockMaterialEntity.builder()
                .material(material)
                .stock(stock)
                .count(dto.getCount())
                .build();

        StockMaterialEntity result = stockMaterialRepository.saveAndFlush(entity);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public StockMaterialResponseDto update(Short stockId, Integer materialId,StockMaterialUpdateRequestDto dto) {
        StockMaterialEntity entity = stockMaterialRepository.findById(dto.getId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getId()))
        );

        MaterialEntity material = materialRepository.findById(materialId).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(materialId))
        );

        StockEntity stock = stockRepository.findById(stockId).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(stockId))
        );

        entity.setMaterial(material);
        entity.setStock(stock);
        entity.setCount(dto.getCount());

        StockMaterialEntity result = stockMaterialRepository.saveAndFlush(entity);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public AckDto delete(Short stockId, Integer materialId) {
        StockMaterialEntity stockMaterial = getStockMaterialOrThrowNotFoundException(stockId, materialId);

        stockMaterialRepository.delete(stockMaterial);

        return AckDto.builder().answer(true).build();
    }

    private StockMaterialEntity getStockMaterialOrThrowNotFoundException(Short stockId, Integer materialId){
        return stockMaterialRepository.findByStockIdAndMaterialId(stockId, materialId).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(stockId + " " + materialId)
        );
    }
}
