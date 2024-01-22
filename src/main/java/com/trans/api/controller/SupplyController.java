package com.trans.api.controller;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.street.StreetCreateDto;
import com.trans.api.dto.street.StreetResponseDto;
import com.trans.api.dto.street.StreetUpdateDto;
import com.trans.api.dto.supply.SupplyCreateRequestDto;
import com.trans.api.dto.supply.SupplyResponseDto;
import com.trans.api.dto.supply.SupplyUpdateRequestDto;
import com.trans.api.service.SupplyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "supplies")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SupplyController {
    SupplyService service;

    public static final String GET_BY_ID = "{id}";

    @GetMapping
    public List<SupplyResponseDto> findAll(){
        return service.findAll();
    }

    @GetMapping(GET_BY_ID)
    public SupplyResponseDto findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SupplyResponseDto create(@RequestBody SupplyCreateRequestDto dto){
        return service.create(dto);
    }

    @PutMapping
    public SupplyResponseDto update(@RequestBody SupplyUpdateRequestDto dto){
        return service.update(dto);
    }

    @DeleteMapping(GET_BY_ID)
    public AckDto delete(@PathVariable Long id){
        return service.delete(id);
    }
}
