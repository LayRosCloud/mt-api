package com.trans.api.dto.counterpartytype;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CounterpartyTypeUpdateRequestDto {
    Short id;
    String name;
}
