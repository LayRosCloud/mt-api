package com.trans.api.dto.bank;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankUpdateRequestDto {
    Short id;
    String name;
}
