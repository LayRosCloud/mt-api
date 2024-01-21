package com.trans.api.service;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.city.CityCreateRequestDto;
import com.trans.api.dto.city.CityResponseDto;
import com.trans.api.dto.city.CityUpdateRequestDto;

import java.util.List;

public interface CityService {
    List<CityResponseDto> findAll();
    CityResponseDto findById(Integer id);
    CityResponseDto create(CityCreateRequestDto dto);
    CityResponseDto update(CityUpdateRequestDto dto);
    AckDto delete(Integer id);
}
