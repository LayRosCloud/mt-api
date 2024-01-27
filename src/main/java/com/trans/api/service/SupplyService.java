package com.trans.api.service;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.supply.SupplyCreateRequestDto;
import com.trans.api.dto.supply.SupplyResponseDto;
import com.trans.api.dto.supply.SupplyUpdateRequestDto;

import java.util.List;

public interface SupplyService {
    List<SupplyResponseDto> findAll();
    SupplyResponseDto findById(Long id);
    SupplyResponseDto create(SupplyCreateRequestDto dto);
    SupplyResponseDto update(SupplyUpdateRequestDto dto);
    AckDto delete(Long id);
}
