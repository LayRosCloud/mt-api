package com.trans.api.service;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.unit.UnitCreateRequestDto;
import com.trans.api.dto.unit.UnitResponseDto;
import com.trans.api.dto.unit.UnitUpdateRequestDto;

import java.util.List;

public interface UnitService {
    List<UnitResponseDto> findAll();
    UnitResponseDto findById(Short id);
    UnitResponseDto create(UnitCreateRequestDto dto);
    UnitResponseDto update(UnitUpdateRequestDto dto);
    AckDto delete(Short id);
}
