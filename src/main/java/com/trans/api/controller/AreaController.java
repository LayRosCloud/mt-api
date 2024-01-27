package com.trans.api.controller;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.area.AreaCreateRequestDto;
import com.trans.api.dto.area.AreaResponseDto;
import com.trans.api.dto.area.AreaUpdateRequestDto;
import com.trans.api.service.AreaService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "areas")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AreaController {
    AreaService service;

    public static final String GET_BY_ID = "{id}";

    @GetMapping
    public List<AreaResponseDto> findAll(){
        return service.findAll();
    }

    @GetMapping(GET_BY_ID)
    public AreaResponseDto findById(@PathVariable Integer id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AreaResponseDto create(@RequestBody AreaCreateRequestDto dto){
        return service.create(dto);
    }

    @PutMapping
    public AreaResponseDto update(@RequestBody AreaUpdateRequestDto dto){
        return service.update(dto);
    }

    @DeleteMapping(GET_BY_ID)
    public AckDto delete(@PathVariable Integer id){
        return service.delete(id);
    }
}
