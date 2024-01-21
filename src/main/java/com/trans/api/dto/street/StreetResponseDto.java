package com.trans.api.dto.street;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StreetResponseDto {
    Integer id;
    String name;
}
