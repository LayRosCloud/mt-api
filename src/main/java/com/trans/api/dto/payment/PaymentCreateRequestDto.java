package com.trans.api.dto.payment;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentCreateRequestDto {
    String number;
    Date date;
    Double sum;
    Long counterpartyId;
}
