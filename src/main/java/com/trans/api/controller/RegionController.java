package com.trans.api.controller;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.payment.PaymentCreateRequestDto;
import com.trans.api.dto.payment.PaymentResponseDto;
import com.trans.api.dto.payment.PaymentUpdateRequestDto;
import com.trans.api.dto.region.RegionCreateRequestDto;
import com.trans.api.dto.region.RegionResponseDto;
import com.trans.api.dto.region.RegionUpdateRequestDto;
import com.trans.api.service.PaymentService;
import com.trans.api.service.RegionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "regions")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class RegionController {
    RegionService service;

    public static final String GET_BY_ID = "{id}";

    @GetMapping
    public List<RegionResponseDto> findAll(){
        return service.findAll();
    }

    @GetMapping(GET_BY_ID)
    public RegionResponseDto findById(@PathVariable Integer id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RegionResponseDto create(@RequestBody RegionCreateRequestDto dto){
        return service.create(dto);
    }

    @PutMapping
    public RegionResponseDto update(@RequestBody RegionUpdateRequestDto dto){
        return service.update(dto);
    }

    @DeleteMapping(GET_BY_ID)
    public AckDto delete(@PathVariable Integer id){
        return service.delete(id);
    }
}
