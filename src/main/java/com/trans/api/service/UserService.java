package com.trans.api.service;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.user.UserCreateRequestDto;
import com.trans.api.dto.user.UserResponseDto;
import com.trans.api.dto.user.UserUpdateRequestDto;

import java.util.List;

public interface UserService {
    List<UserResponseDto> findAll();
    UserResponseDto findById(Long id);
    UserResponseDto create(UserCreateRequestDto dto);
    UserResponseDto update(UserUpdateRequestDto dto);
    AckDto delete(Long id);
}
