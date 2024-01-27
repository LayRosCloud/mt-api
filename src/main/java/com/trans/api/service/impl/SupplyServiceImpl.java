package com.trans.api.service.impl;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.supply.SupplyCreateRequestDto;
import com.trans.api.dto.supply.SupplyResponseDto;
import com.trans.api.dto.supply.SupplyUpdateRequestDto;
import com.trans.api.entity.CounterpartyEntity;
import com.trans.api.entity.StockMaterialEntity;
import com.trans.api.entity.SupplyEntity;
import com.trans.api.mapper.SupplyMapper;
import com.trans.api.repository.CounterpartyRepository;
import com.trans.api.repository.StockMaterialRepository;
import com.trans.api.repository.SupplyRepository;
import com.trans.api.scripts.helpers.ThrowableHelper;
import com.trans.api.service.SupplyService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SupplyServiceImpl implements SupplyService {
    SupplyRepository supplyRepository;
    StockMaterialRepository stockMaterialRepository;
    CounterpartyRepository counterpartyRepository;
    SupplyMapper mapper;

    @Override
    public List<SupplyResponseDto> findAll() {
        List<SupplyEntity> supplies = supplyRepository.findAll();

        return mapper.toListDto(supplies);
    }

    @Override
    public SupplyResponseDto findById(Long id) {
        SupplyEntity supply = getSupplyOrThrowNotFoundException(id);
        return mapper.toDto(supply);
    }

    @Override
    @Transactional
    public SupplyResponseDto create(SupplyCreateRequestDto dto) {
        CounterpartyEntity counterparty = counterpartyRepository.findById(dto.getCounterpartyId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getCounterpartyId()))
        );

        StockMaterialEntity stockMaterial = stockMaterialRepository.findById(dto.getStockMaterialId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getStockMaterialId()))
        );

        SupplyEntity supply = SupplyEntity.builder()
                .stockMaterial(stockMaterial)
                .counterparty(counterparty)
                .count(dto.getCount())
                .sum(dto.getSum())
                .price(dto.getPrice())
                .build();

        SupplyEntity result = supplyRepository.saveAndFlush(supply);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public SupplyResponseDto update(SupplyUpdateRequestDto dto) {
        SupplyEntity supply = getSupplyOrThrowNotFoundException(dto.getId());

        CounterpartyEntity counterparty = counterpartyRepository.findById(dto.getCounterpartyId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getCounterpartyId()))
        );

        StockMaterialEntity stockMaterial = stockMaterialRepository.findById(dto.getStockMaterialId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getStockMaterialId()))
        );

        supply.setCount(dto.getCount());
        supply.setStockMaterial(stockMaterial);
        supply.setCounterparty(counterparty);
        supply.setSum(dto.getSum());
        supply.setPrice(dto.getPrice());

        SupplyEntity result = supplyRepository.saveAndFlush(supply);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public AckDto delete(Long id) {
        SupplyEntity supply = getSupplyOrThrowNotFoundException(id);
        supplyRepository.delete(supply);
        return AckDto.builder()
                .answer(true)
                .build();
    }

    private SupplyEntity getSupplyOrThrowNotFoundException(Long id){
        return supplyRepository.findById(id).orElseThrow(()->
                        ThrowableHelper.throwNotFoundException(String.valueOf(id))
                );
    }
}
