package com.trans.api.dto.counterparty;

import com.trans.api.dto.address.AddressResponseDto;
import com.trans.api.dto.counterpartytype.CounterpartyTypeResponseDto;
import com.trans.api.entity.BankEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CounterpartyResponseDto {
    Long id;
    String inn;
    String kpp;
    String ogrn;
    String correspondentAccount;
    String bik;
    String phone;
    String email;
    CounterpartyTypeResponseDto type;
    AddressResponseDto address;
    BankEntity bank;
}
