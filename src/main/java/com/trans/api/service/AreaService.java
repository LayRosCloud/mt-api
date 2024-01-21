package com.trans.api.service;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.area.AreaCreateRequestDto;
import com.trans.api.dto.area.AreaResponseDto;
import com.trans.api.dto.area.AreaUpdateRequestDto;

import java.util.List;

public interface AreaService {
    List<AreaResponseDto> findAll();
    AreaResponseDto findById(Integer id);
    AreaResponseDto create(AreaCreateRequestDto dto);
    AreaResponseDto update(AreaUpdateRequestDto dto);
    AckDto delete(Integer id);
}
