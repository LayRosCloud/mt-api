package com.trans.api.service;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.region.RegionCreateRequestDto;
import com.trans.api.dto.region.RegionResponseDto;
import com.trans.api.dto.region.RegionUpdateRequestDto;

import java.util.List;

public interface RegionService {
    List<RegionResponseDto> findAll();
    RegionResponseDto findById(Integer id);
    RegionResponseDto create(RegionCreateRequestDto dto);
    RegionResponseDto update(RegionUpdateRequestDto dto);
    AckDto delete(Integer id);
}
