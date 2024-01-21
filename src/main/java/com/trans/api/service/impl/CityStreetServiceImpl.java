package com.trans.api.service.impl;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.citystreet.CityStreetCreateRequestDto;
import com.trans.api.dto.citystreet.CityStreetResponseDto;
import com.trans.api.dto.citystreet.CityStreetUpdateRequestDto;
import com.trans.api.service.CityStreetService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityStreetServiceImpl implements CityStreetService {
    @Override
    public List<CityStreetResponseDto> findAll() {
        return null;
    }

    @Override
    public CityStreetResponseDto findById(Long id) {
        return null;
    }

    @Override
    public CityStreetResponseDto create(CityStreetCreateRequestDto dto) {
        return null;
    }

    @Override
    public CityStreetResponseDto update(CityStreetUpdateRequestDto dto) {
        return null;
    }

    @Override
    public AckDto delete(Long id) {
        return null;
    }
}
