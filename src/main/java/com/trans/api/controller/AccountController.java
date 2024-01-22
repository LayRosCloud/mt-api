package com.trans.api.controller;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.account.AccountCreateRequestDto;
import com.trans.api.dto.account.AccountResponseDto;
import com.trans.api.dto.account.AccountUpdateRequestDto;
import com.trans.api.service.AccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "accounts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountController {
    AccountService service;

    public static final String GET_BY_ID = "{id}";

    @GetMapping
    public List<AccountResponseDto> findAll(){
        return service.findAll();
    }

    @GetMapping(GET_BY_ID)
    public AccountResponseDto findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    public AccountResponseDto create(@RequestBody AccountCreateRequestDto dto){
        return service.create(dto);
    }

    @PutMapping
    public AccountResponseDto update(@RequestBody AccountUpdateRequestDto dto){
        return service.update(dto);
    }

    @DeleteMapping(GET_BY_ID)
    public AckDto delete(@PathVariable Long id){
        return service.delete(id);
    }
}
