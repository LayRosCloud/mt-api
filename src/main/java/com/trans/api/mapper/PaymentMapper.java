package com.trans.api.mapper;

import com.trans.api.dto.payment.PaymentResponseDto;
import com.trans.api.entity.PaymentEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = { CounterpartyMapper.class })
public interface PaymentMapper {
    List<PaymentResponseDto> toListDto(List<PaymentEntity> entities);
    PaymentResponseDto toDto(PaymentEntity entity);
}
