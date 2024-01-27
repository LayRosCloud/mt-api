package com.trans.api.controller;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.area.AreaCreateRequestDto;
import com.trans.api.dto.area.AreaResponseDto;
import com.trans.api.dto.area.AreaUpdateRequestDto;
import com.trans.api.dto.bank.BankCreateRequestDto;
import com.trans.api.dto.bank.BankResponseDto;
import com.trans.api.dto.bank.BankUpdateRequestDto;
import com.trans.api.service.BankService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "banks")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BankController {
    BankService service;

    public static final String GET_BY_ID = "{id}";

    @GetMapping
    public List<BankResponseDto> findAll(){
        return service.findAll();
    }

    @GetMapping(GET_BY_ID)
    public BankResponseDto findById(@PathVariable Short id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BankResponseDto create(@RequestBody BankCreateRequestDto dto){
        return service.create(dto);
    }

    @PutMapping
    public BankResponseDto update(@RequestBody BankUpdateRequestDto dto){
        return service.update(dto);
    }

    @DeleteMapping(GET_BY_ID)
    public AckDto delete(@PathVariable Short id){
        return service.delete(id);
    }
}
