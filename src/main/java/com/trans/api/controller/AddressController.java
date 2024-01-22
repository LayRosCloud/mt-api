package com.trans.api.controller;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.address.AddressCreateRequestDto;
import com.trans.api.dto.address.AddressResponseDto;
import com.trans.api.dto.address.AddressUpdateRequestDto;
import com.trans.api.service.AddressService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "addresses")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddressController {
    AddressService service;

    public static final String GET_BY_ID = "{id}";

    @GetMapping
    public List<AddressResponseDto> findAll(){
        return service.findAll();
    }

    @GetMapping(GET_BY_ID)
    public AddressResponseDto findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AddressResponseDto create(@RequestBody AddressCreateRequestDto dto){
        return service.create(dto);
    }

    @PutMapping
    public AddressResponseDto update(@RequestBody AddressUpdateRequestDto dto){
        return service.update(dto);
    }

    @DeleteMapping(GET_BY_ID)
    public AckDto delete(@PathVariable Long id){
        return service.delete(id);
    }

}
