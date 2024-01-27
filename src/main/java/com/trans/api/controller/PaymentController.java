package com.trans.api.controller;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.payment.PaymentCreateRequestDto;
import com.trans.api.dto.payment.PaymentResponseDto;
import com.trans.api.dto.payment.PaymentUpdateRequestDto;
import com.trans.api.service.PaymentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "payments")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PaymentController {
    PaymentService service;

    public static final String GET_BY_ID = "{id}";

    @GetMapping
    public List<PaymentResponseDto> findAll(){
        return service.findAll();
    }

    @GetMapping(GET_BY_ID)
    public PaymentResponseDto findById(@PathVariable Integer id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponseDto create(@RequestBody PaymentCreateRequestDto dto){
        return service.create(dto);
    }

    @PutMapping
    public PaymentResponseDto update(@RequestBody PaymentUpdateRequestDto dto){
        return service.update(dto);
    }

    @DeleteMapping(GET_BY_ID)
    public AckDto delete(@PathVariable Integer id){
        return service.delete(id);
    }
}
