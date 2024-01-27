package com.trans.api.dto.unit;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UnitUpdateRequestDto {
    Short id;
    String name;
}
