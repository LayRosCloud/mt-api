package com.trans.api.dto.country;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CountryUpdateRequestDto {
    Short id;
    String name;
}
