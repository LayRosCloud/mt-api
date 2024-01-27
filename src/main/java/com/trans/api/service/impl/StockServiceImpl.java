package com.trans.api.service.impl;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.stock.StockCreateRequestDto;
import com.trans.api.dto.stock.StockResponseDto;
import com.trans.api.dto.stock.StockUpdateRequestDto;
import com.trans.api.entity.AddressEntity;
import com.trans.api.entity.StockEntity;
import com.trans.api.mapper.StockMapper;
import com.trans.api.repository.AddressRepository;
import com.trans.api.repository.StockRepository;
import com.trans.api.scripts.helpers.ThrowableHelper;
import com.trans.api.service.AddressService;
import com.trans.api.service.StockService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class StockServiceImpl implements StockService {

    StockRepository stockRepository;
    AddressRepository addressRepository;
    StockMapper mapper;

    @Override
    public List<StockResponseDto> findAll() {
        List<StockEntity> stocks = stockRepository.findAll();
        return mapper.toListDto(stocks);
    }

    @Override
    public StockResponseDto findById(Short id) {
        StockEntity stock = getStockOrThrowNotFoundException(id);
        return mapper.toDto(stock);
    }

    @Override
    @Transactional
    public StockResponseDto create(StockCreateRequestDto dto) {
        AddressEntity address = addressRepository.findById(dto.getAddressId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getAddressId()))
        );

        StockEntity stock = StockEntity.builder()
                .name(dto.getName())
                .address(address)
                .build();

        StockEntity result = stockRepository.saveAndFlush(stock);
        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public StockResponseDto update(StockUpdateRequestDto dto) {
        StockEntity stock = getStockOrThrowNotFoundException(dto.getId());
        AddressEntity address = addressRepository.findById(dto.getAddressId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getAddressId()))
        );
        stock.setAddress(address);
        stock.setName(dto.getName());

        StockEntity result = stockRepository.saveAndFlush(stock);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public AckDto delete(Short id) {
        StockEntity stock = getStockOrThrowNotFoundException(id);

        stockRepository.delete(stock);

        return AckDto.builder()
                .answer(true)
                .build();
    }

    private StockEntity getStockOrThrowNotFoundException(Short id){
        return stockRepository.findById(id).orElseThrow(()->
                        ThrowableHelper.throwNotFoundException(String.valueOf(id))
                );
    }
}
