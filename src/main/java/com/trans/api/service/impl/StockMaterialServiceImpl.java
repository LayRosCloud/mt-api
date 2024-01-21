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
            stocks.addAll(stockMaterialRepository.findByStockId(stockId));
        }

        return mapper.toListDto(stocks);
    }

    @Override
    public StockMaterialResponseDto findByStockIdAndMaterialId(Short stockId, Integer materialId) {
        StockMaterialEntity stockMaterial = stockMaterialRepository
                .findByStockIdAndMaterialId(stockId, materialId).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(stockId + " " + materialId)
        );
        return mapper.toDto(stockMaterial);
    }

    @Override
    @Transactional
    public StockMaterialResponseDto create(StockMaterialCreateRequestDto dto) {

        MaterialEntity material = materialRepository.findById(dto.getMaterialId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getMaterialId()))
        );

        StockEntity stock = stockRepository.findById(dto.getStockId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getStockId()))
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
    public StockMaterialResponseDto update(StockMaterialUpdateRequestDto dto) {
        StockMaterialEntity entity = getStockMaterialOrThrowNotFoundException(dto.getId());

        MaterialEntity material = materialRepository.findById(dto.getMaterialId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getMaterialId()))
        );

        StockEntity stock = stockRepository.findById(dto.getStockId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getStockId()))
        );

        entity.setMaterial(material);
        entity.setStock(stock);
        entity.setCount(dto.getCount());

        StockMaterialEntity result = stockMaterialRepository.saveAndFlush(entity);

        return mapper.toDto(result);
    }

    @Override
    public AckDto delete(Integer id) {
        StockMaterialEntity stockMaterial = getStockMaterialOrThrowNotFoundException(id);

        stockMaterialRepository.delete(stockMaterial);

        return AckDto.builder().answer(true).build();
    }

    private StockMaterialEntity getStockMaterialOrThrowNotFoundException(Integer id){
        return stockMaterialRepository.findById(id).orElseThrow(()->
                        ThrowableHelper.throwNotFoundException(String.valueOf(id))
                );
    }
}
