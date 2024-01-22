package com.trans.api.service;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.citystreet.CityStreetCreateRequestDto;
import com.trans.api.dto.citystreet.CityStreetResponseDto;
import com.trans.api.dto.citystreet.CityStreetUpdateRequestDto;

import java.util.List;

public interface CityStreetService {
    List<CityStreetResponseDto> findAll(Integer cityId);
    CityStreetResponseDto findByCityIdAndStreetId(Integer cityId, Integer streetId);
    CityStreetResponseDto create(Integer cityId, Integer streetId, CityStreetCreateRequestDto dto);
    CityStreetResponseDto update(Integer cityId, Integer streetId, CityStreetUpdateRequestDto dto);
    AckDto delete(Integer cityId, Integer streetId);
}
