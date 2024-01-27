package com.trans.api.service;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.payment.PaymentCreateRequestDto;
import com.trans.api.dto.payment.PaymentResponseDto;
import com.trans.api.dto.payment.PaymentUpdateRequestDto;

import java.util.List;

public interface PaymentService {
    List<PaymentResponseDto> findAll();
    PaymentResponseDto findById(Integer id);
    PaymentResponseDto create(PaymentCreateRequestDto dto);
    PaymentResponseDto update(PaymentUpdateRequestDto dto);
    AckDto delete(Integer id);
}
