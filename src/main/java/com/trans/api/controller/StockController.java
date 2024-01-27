package com.trans.api.controller;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.region.RegionCreateRequestDto;
import com.trans.api.dto.region.RegionResponseDto;
import com.trans.api.dto.region.RegionUpdateRequestDto;
import com.trans.api.dto.stock.StockCreateRequestDto;
import com.trans.api.dto.stock.StockResponseDto;
import com.trans.api.dto.stock.StockUpdateRequestDto;
import com.trans.api.service.RegionService;
import com.trans.api.service.StockService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "stocks")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class StockController {
    StockService service;

    public static final String GET_BY_ID = "{id}";

    @GetMapping
    public List<StockResponseDto> findAll(){
        return service.findAll();
    }

    @GetMapping(GET_BY_ID)
    public StockResponseDto findById(@PathVariable Short id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StockResponseDto create(@RequestBody StockCreateRequestDto dto){
        return service.create(dto);
    }

    @PutMapping
    public StockResponseDto update(@RequestBody StockUpdateRequestDto dto){
        return service.update(dto);
    }

    @DeleteMapping(GET_BY_ID)
    public AckDto delete(@PathVariable Short id){
        return service.delete(id);
    }
}
