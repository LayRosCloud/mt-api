package com.trans.api.service;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.account.AccountCreateRequestDto;
import com.trans.api.dto.account.AccountResponseDto;
import com.trans.api.dto.account.AccountUpdateRequestDto;
import com.trans.api.dto.address.AddressCreateRequestDto;
import com.trans.api.dto.address.AddressResponseDto;
import com.trans.api.dto.address.AddressUpdateRequestDto;

import java.util.List;

public interface AddressService {
    List<AddressResponseDto> findAll();
    AddressResponseDto findById(Long id);
    AddressResponseDto create(AddressCreateRequestDto dto);
    AddressResponseDto update(AddressUpdateRequestDto dto);
    AckDto delete(Long id);
}
