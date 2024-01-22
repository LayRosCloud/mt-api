package com.trans.api.service.impl;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.payment.PaymentCreateRequestDto;
import com.trans.api.dto.payment.PaymentResponseDto;
import com.trans.api.dto.payment.PaymentUpdateRequestDto;
import com.trans.api.entity.CounterpartyEntity;
import com.trans.api.entity.PaymentEntity;
import com.trans.api.mapper.PaymentMapper;
import com.trans.api.repository.CounterpartyRepository;
import com.trans.api.repository.PaymentRepository;
import com.trans.api.scripts.helpers.ThrowableHelper;
import com.trans.api.service.PaymentService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class PaymentServiceImpl implements PaymentService {

    PaymentRepository paymentRepository;
    CounterpartyRepository counterpartyRepository;
    PaymentMapper mapper;

    @Override
    public List<PaymentResponseDto> findAll() {
        List<PaymentEntity> payments = paymentRepository.findAll();

        return mapper.toListDto(payments);
    }

    @Override
    public PaymentResponseDto findById(Integer id) {
        PaymentEntity payment = getPaymentOrThrowNotFoundException(id);
        return mapper.toDto(payment);
    }

    @Override
    @Transactional
    public PaymentResponseDto create(PaymentCreateRequestDto dto) {
        CounterpartyEntity counterparty = counterpartyRepository.findById(dto.getCounterpartyId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getCounterpartyId()))
        );

        PaymentEntity payment = PaymentEntity.builder()
                .counterparty(counterparty)
                .date(dto.getDate())
                .number(dto.getNumber())
                .sum(dto.getSum())
                .build();

        PaymentEntity result = paymentRepository.saveAndFlush(payment);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public PaymentResponseDto update(PaymentUpdateRequestDto dto) {
        PaymentEntity payment = getPaymentOrThrowNotFoundException(dto.getId());

        CounterpartyEntity counterparty = counterpartyRepository.findById(dto.getCounterpartyId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getCounterpartyId()))
        );

        payment.setCounterparty(counterparty);
        payment.setNumber(dto.getNumber());
        payment.setDate(payment.getDate());
        payment.setSum(payment.getSum());

        PaymentEntity result = paymentRepository.saveAndFlush(payment);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public AckDto delete(Integer id) {
        PaymentEntity payment = getPaymentOrThrowNotFoundException(id);

        paymentRepository.delete(payment);

        return AckDto.builder().answer(true).build();
    }

    private PaymentEntity getPaymentOrThrowNotFoundException(Integer id){
        return paymentRepository.findById(id).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(id))
        );
    }
}
