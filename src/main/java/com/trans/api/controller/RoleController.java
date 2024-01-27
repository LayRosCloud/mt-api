package com.trans.api.controller;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.role.RoleCreateRequestDto;
import com.trans.api.dto.role.RoleResponseDto;
import com.trans.api.dto.role.RoleUpdateRequestDto;
import com.trans.api.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "roles")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleController {
    public static final String GET_BY_ID = "{id}";

    RoleService service;

    @GetMapping
    public List<RoleResponseDto> findAll(){
        return service.findAll();
    }

    @GetMapping(GET_BY_ID)
    public RoleResponseDto findById(@PathVariable Short id){
        return service.findById(id);
    }

    @PostMapping
    public RoleResponseDto create(@RequestBody RoleCreateRequestDto dto){
        return service.create(dto);
    }

    @PutMapping
    public RoleResponseDto update(@RequestBody RoleUpdateRequestDto dto){
        return service.update(dto);
    }

    @DeleteMapping(GET_BY_ID)
    public AckDto delete(@PathVariable Short id){
        return service.delete(id);
    }
}
