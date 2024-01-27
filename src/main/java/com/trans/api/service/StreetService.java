package com.trans.api.service;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.street.StreetCreateDto;
import com.trans.api.dto.street.StreetResponseDto;
import com.trans.api.dto.street.StreetUpdateDto;

import java.util.List;

public interface StreetService {
    List<StreetResponseDto> findAll();
    StreetResponseDto findById(Integer id);
    StreetResponseDto create(StreetCreateDto dto);
    StreetResponseDto update(StreetUpdateDto dto);
    AckDto delete(Integer id);
}
