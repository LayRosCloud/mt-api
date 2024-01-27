package com.trans.api.controller;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.stock.StockCreateRequestDto;
import com.trans.api.dto.stock.StockResponseDto;
import com.trans.api.dto.stock.StockUpdateRequestDto;
import com.trans.api.dto.street.StreetCreateDto;
import com.trans.api.dto.street.StreetResponseDto;
import com.trans.api.dto.street.StreetUpdateDto;
import com.trans.api.service.StockService;
import com.trans.api.service.StreetService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "streets")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class StreetController {
    StreetService service;

    public static final String GET_BY_ID = "{id}";

    @GetMapping
    public List<StreetResponseDto> findAll(){
        return service.findAll();
    }

    @GetMapping(GET_BY_ID)
    public StreetResponseDto findById(@PathVariable Integer id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StreetResponseDto create(@RequestBody StreetCreateDto dto){
        return service.create(dto);
    }

    @PutMapping
    public StreetResponseDto update(@RequestBody StreetUpdateDto dto){
        return service.update(dto);
    }

    @DeleteMapping(GET_BY_ID)
    public AckDto delete(@PathVariable Integer id){
        return service.delete(id);
    }
}
