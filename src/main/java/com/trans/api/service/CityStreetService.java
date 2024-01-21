package com.trans.api.service;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.citystreet.CityStreetCreateRequestDto;
import com.trans.api.dto.citystreet.CityStreetResponseDto;
import com.trans.api.dto.citystreet.CityStreetUpdateRequestDto;

import java.util.List;

public interface CityStreetService {
    List<CityStreetResponseDto> findAll();
    CityStreetResponseDto findById(Long id);
    CityStreetResponseDto create(CityStreetCreateRequestDto dto);
    CityStreetResponseDto update(CityStreetUpdateRequestDto dto);
    AckDto delete(Long id);
}
