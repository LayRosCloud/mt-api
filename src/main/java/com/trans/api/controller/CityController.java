package com.trans.api.controller;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.bank.BankCreateRequestDto;
import com.trans.api.dto.bank.BankResponseDto;
import com.trans.api.dto.bank.BankUpdateRequestDto;
import com.trans.api.dto.city.CityCreateRequestDto;
import com.trans.api.dto.city.CityResponseDto;
import com.trans.api.dto.city.CityUpdateRequestDto;
import com.trans.api.service.BankService;
import com.trans.api.service.CityService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "cities")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CityController {
    CityService service;

    public static final String GET_BY_ID = "{id}";

    @GetMapping
    public List<CityResponseDto> findAll(){
        return service.findAll();
    }

    @GetMapping(GET_BY_ID)
    public CityResponseDto findById(@PathVariable Integer id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CityResponseDto create(@RequestBody CityCreateRequestDto dto){
        return service.create(dto);
    }

    @PutMapping
    public CityResponseDto update(@RequestBody CityUpdateRequestDto dto){
        return service.update(dto);
    }

    @DeleteMapping(GET_BY_ID)
    public AckDto delete(@PathVariable Integer id){
        return service.delete(id);
    }
}
