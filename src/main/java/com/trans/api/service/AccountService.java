package com.trans.api.service;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.account.AccountCreateRequestDto;
import com.trans.api.dto.account.AccountResponseDto;
import com.trans.api.dto.account.AccountUpdateRequestDto;

import java.util.List;

public interface AccountService {
    List<AccountResponseDto> findAll();
    AccountResponseDto findById(Long id);
    AccountResponseDto create(AccountCreateRequestDto dto);
    AccountResponseDto update(AccountUpdateRequestDto dto);
    AckDto delete(Long id);
}
