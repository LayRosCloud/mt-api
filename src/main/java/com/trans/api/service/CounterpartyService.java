package com.trans.api.service;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.counterparty.CounterpartyCreateRequestDto;
import com.trans.api.dto.counterparty.CounterpartyResponseDto;
import com.trans.api.dto.counterparty.CounterpartyUpdateRequestDto;

import java.util.List;

public interface CounterpartyService {
    List<CounterpartyResponseDto> findAll();
    CounterpartyResponseDto findById(Long id);
    CounterpartyResponseDto create(CounterpartyCreateRequestDto dto);
    CounterpartyResponseDto update(CounterpartyUpdateRequestDto dto);
    AckDto delete(Long id);
}
