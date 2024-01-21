package com.trans.api.service;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.country.CountryCreateRequestDto;
import com.trans.api.dto.country.CountryResponseDto;
import com.trans.api.dto.country.CountryUpdateRequestDto;

import java.util.List;

public interface CountryService {
    List<CountryResponseDto> findAll();
    CountryResponseDto findById(Short id);
    CountryResponseDto create(CountryCreateRequestDto dto);
    CountryResponseDto update(CountryUpdateRequestDto dto);
    AckDto delete(Short id);
}
