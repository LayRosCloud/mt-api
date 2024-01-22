package com.trans.api.controller;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.unit.UnitCreateRequestDto;
import com.trans.api.dto.unit.UnitResponseDto;
import com.trans.api.dto.unit.UnitUpdateRequestDto;
import com.trans.api.service.UnitService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "units")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UnitController {
    UnitService service;

    public static final String GET_BY_ID = "{id}";

    @GetMapping
    public List<UnitResponseDto> findAll(){
        return service.findAll();
    }

    @GetMapping(GET_BY_ID)
    public UnitResponseDto findById(@PathVariable Short id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UnitResponseDto create(@RequestBody UnitCreateRequestDto dto){
        return service.create(dto);
    }

    @PutMapping
    public UnitResponseDto update(@RequestBody UnitUpdateRequestDto dto){
        return service.update(dto);
    }

    @DeleteMapping(GET_BY_ID)
    public AckDto delete(@PathVariable Short id){
        return service.delete(id);
    }

}
