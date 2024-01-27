package com.trans.api.controller;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.city.CityCreateRequestDto;
import com.trans.api.dto.city.CityResponseDto;
import com.trans.api.dto.city.CityUpdateRequestDto;
import com.trans.api.dto.counterparty.CounterpartyCreateRequestDto;
import com.trans.api.dto.counterparty.CounterpartyResponseDto;
import com.trans.api.dto.counterparty.CounterpartyUpdateRequestDto;
import com.trans.api.service.CityService;
import com.trans.api.service.CounterpartyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "counterparties")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CounterpartyController {
    CounterpartyService service;

    public static final String GET_BY_ID = "{id}";

    @GetMapping
    public List<CounterpartyResponseDto> findAll(){
        return service.findAll();
    }

    @GetMapping(GET_BY_ID)
    public CounterpartyResponseDto findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CounterpartyResponseDto create(@RequestBody CounterpartyCreateRequestDto dto){
        return service.create(dto);
    }

    @PutMapping
    public CounterpartyResponseDto update(@RequestBody CounterpartyUpdateRequestDto dto){
        return service.update(dto);
    }

    @DeleteMapping(GET_BY_ID)
    public AckDto delete(@PathVariable Long id){
        return service.delete(id);
    }
}
