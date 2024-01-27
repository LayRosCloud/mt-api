package com.trans.api.dto.payment;

import com.trans.api.entity.CounterpartyEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentResponseDto {
    Integer id;
    String number;
    Date date;
    Double sum;
    CounterpartyEntity counterparty;
}
