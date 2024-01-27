package com.trans.api.dto.counterparty;

import com.trans.api.dto.address.AddressResponseDto;
import com.trans.api.dto.counterpartytype.CounterpartyTypeResponseDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CounterpartyUpdateRequestDto {
    Long id;
    Long addressId;
    String inn;
    String kpp;
    String ogrn;
    String correspondentAccount;
    String bik;
    String phone;
    String email;
    Short bankId;
    Short typeId;
}
