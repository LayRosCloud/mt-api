package com.trans.api.service;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.role.RoleCreateRequestDto;
import com.trans.api.dto.role.RoleResponseDto;
import com.trans.api.dto.role.RoleUpdateRequestDto;

import java.util.List;

public interface RoleService {
    List<RoleResponseDto> findAll();
    RoleResponseDto findById(Short id);
    RoleResponseDto create(RoleCreateRequestDto dto);
    RoleResponseDto update(RoleUpdateRequestDto dto);
    AckDto delete(Short id);
}
