package com.trans.api.dto.counterparty;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CounterpartyCreateRequestDto {
    Long id;
    Long addressId;
    String inn;
    String kpp;
    String ogrn;
    Short bankId;
    String correspondentAccount;
    String bik;
    String phone;
    String email;
    Short typeId;
}
