package com.trans.api.service.impl;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.payment.PaymentCreateRequestDto;
import com.trans.api.dto.payment.PaymentResponseDto;
import com.trans.api.dto.payment.PaymentUpdateRequestDto;
import com.trans.api.service.PaymentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Override
    public List<PaymentResponseDto> findAll() {
        return null;
    }

    @Override
    public PaymentResponseDto findById(Integer id) {
        return null;
    }

    @Override
    public PaymentResponseDto create(PaymentCreateRequestDto dto) {
        return null;
    }

    @Override
    public PaymentResponseDto update(PaymentUpdateRequestDto dto) {
        return null;
    }

    @Override
    public AckDto delete(Integer id) {
        return null;
    }
}
