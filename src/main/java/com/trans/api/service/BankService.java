package com.trans.api.service;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.bank.BankCreateRequestDto;
import com.trans.api.dto.bank.BankResponseDto;
import com.trans.api.dto.bank.BankUpdateRequestDto;

import java.util.List;

public interface BankService {
    List<BankResponseDto> findAll();
    BankResponseDto findById(Short id);
    BankResponseDto create(BankCreateRequestDto dto);
    BankResponseDto update(BankUpdateRequestDto dto);
    AckDto delete(Short id);
}
