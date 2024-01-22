package com.trans.api.controller;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.country.CountryCreateRequestDto;
import com.trans.api.dto.country.CountryResponseDto;
import com.trans.api.dto.country.CountryUpdateRequestDto;
import com.trans.api.dto.material.MaterialCreateRequestDto;
import com.trans.api.dto.material.MaterialResponseDto;
import com.trans.api.dto.material.MaterialUpdateRequestDto;
import com.trans.api.service.MaterialService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "materials")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class MaterialController {
    MaterialService service;

    public static final String GET_BY_ID = "{id}";

    @GetMapping
    public List<MaterialResponseDto> findAll(){
        return service.findAll();
    }

    @GetMapping(GET_BY_ID)
    public MaterialResponseDto findById(@PathVariable Integer id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MaterialResponseDto create(@RequestBody MaterialCreateRequestDto dto){
        return service.create(dto);
    }

    @PutMapping
    public MaterialResponseDto update(@RequestBody MaterialUpdateRequestDto dto){
        return service.update(dto);
    }

    @DeleteMapping(GET_BY_ID)
    public AckDto delete(@PathVariable Integer id){
        return service.delete(id);
    }
}
