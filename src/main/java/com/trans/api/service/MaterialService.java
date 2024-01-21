package com.trans.api.service;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.material.MaterialCreateRequestDto;
import com.trans.api.dto.material.MaterialResponseDto;
import com.trans.api.dto.material.MaterialUpdateRequestDto;

import java.util.List;

public interface MaterialService {
    List<MaterialResponseDto> findAll();
    MaterialResponseDto findById(Integer id);
    MaterialResponseDto create(MaterialCreateRequestDto dto);
    MaterialResponseDto update(MaterialUpdateRequestDto dto);
    AckDto delete(Integer id);
}
