package com.trans.api.controller;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.user.UserCreateRequestDto;
import com.trans.api.dto.user.UserResponseDto;
import com.trans.api.dto.user.UserUpdateRequestDto;
import com.trans.api.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "users")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {

    UserService service;
    public static final String GET_BY_ID = "{id}";

    @GetMapping
    public List<UserResponseDto> findAll(){
        return service.findAll();
    }

    @GetMapping(GET_BY_ID)
    public UserResponseDto findById(@PathVariable Long id){
        return service.findById(id);
    }

    @PostMapping
    public UserResponseDto create(@RequestBody UserCreateRequestDto dto){
        return service.create(dto);
    }

    @PutMapping
    public UserResponseDto update(@RequestBody UserUpdateRequestDto dto){
        return service.update(dto);
    }

    @DeleteMapping(GET_BY_ID)
    public AckDto delete(@PathVariable Long id){
        return service.delete(id);
    }
}
