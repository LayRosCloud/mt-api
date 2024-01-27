package com.trans.api.dto.counterpartytype;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CounterpartyTypeCreateRequestDto {
    String name;
}
