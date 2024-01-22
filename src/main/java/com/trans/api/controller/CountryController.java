package com.trans.api.controller;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.counterpartytype.CounterpartyTypeCreateRequestDto;
import com.trans.api.dto.counterpartytype.CounterpartyTypeResponseDto;
import com.trans.api.dto.counterpartytype.CounterpartyTypeUpdateRequestDto;
import com.trans.api.dto.country.CountryCreateRequestDto;
import com.trans.api.dto.country.CountryResponseDto;
import com.trans.api.dto.country.CountryUpdateRequestDto;
import com.trans.api.entity.CountryEntity;
import com.trans.api.service.CountryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "countries")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CountryController {

    CountryService service;

    public static final String GET_BY_ID = "{id}";

    @GetMapping
    public List<CountryResponseDto> findAll(){
        return service.findAll();
    }

    @GetMapping(GET_BY_ID)
    public CountryResponseDto findById(@PathVariable Short id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CountryResponseDto create(@RequestBody CountryCreateRequestDto dto){
        return service.create(dto);
    }

    @PutMapping
    public CountryResponseDto update(@RequestBody CountryUpdateRequestDto dto){
        return service.update(dto);
    }

    @DeleteMapping(GET_BY_ID)
    public AckDto delete(@PathVariable Short id){
        return service.delete(id);
    }
}
