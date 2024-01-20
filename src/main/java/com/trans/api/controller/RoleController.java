package com.trans.api.controller;

import com.trans.api.dto.role.RoleCreateRequestDto;
import com.trans.api.dto.role.RoleResponseDto;
import com.trans.api.dto.role.RoleUpdateRequestDto;
import com.trans.api.entity.RoleEntity;
import com.trans.api.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService service;

    @GetMapping
    public List<RoleResponseDto> findAll(){
        return service.findAll();
    }

    @GetMapping("{id}")
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

    @DeleteMapping("{id}")
    public RoleResponseDto delete(@PathVariable Short id){
        return service.delete(id);
    }
}
