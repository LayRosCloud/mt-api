package com.trans.api.controller;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.counterpartytype.CounterpartyTypeCreateRequestDto;
import com.trans.api.dto.counterpartytype.CounterpartyTypeResponseDto;
import com.trans.api.dto.counterpartytype.CounterpartyTypeUpdateRequestDto;
import com.trans.api.service.CounterpartyTypeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "counterparties/types")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CounterpartyTypeController {
    CounterpartyTypeService service;

    public static final String GET_BY_ID = "{id}";

    @GetMapping
    public List<CounterpartyTypeResponseDto> findAll(){
        return service.findAll();
    }

    @GetMapping(GET_BY_ID)
    public CounterpartyTypeResponseDto findById(@PathVariable Short id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CounterpartyTypeResponseDto create(@RequestBody CounterpartyTypeCreateRequestDto dto){
        return service.create(dto);
    }

    @PutMapping
    public CounterpartyTypeResponseDto update(@RequestBody CounterpartyTypeUpdateRequestDto dto){
        return service.update(dto);
    }

    @DeleteMapping(GET_BY_ID)
    public AckDto delete(@PathVariable Short id){
        return service.delete(id);
    }
}
