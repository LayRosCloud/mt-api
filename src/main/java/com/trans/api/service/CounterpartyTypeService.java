package com.trans.api.service;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.counterpartytype.CounterpartyTypeCreateRequestDto;
import com.trans.api.dto.counterpartytype.CounterpartyTypeResponseDto;
import com.trans.api.dto.counterpartytype.CounterpartyTypeUpdateRequestDto;

import java.util.List;

public interface CounterpartyTypeService {
    List<CounterpartyTypeResponseDto> findAll();
    CounterpartyTypeResponseDto findById(Short id);
    CounterpartyTypeResponseDto create(CounterpartyTypeCreateRequestDto dto);
    CounterpartyTypeResponseDto update(CounterpartyTypeUpdateRequestDto dto);
    AckDto delete(Short id);
}
