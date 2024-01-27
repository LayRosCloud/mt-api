package com.trans.api.dto.citystreet;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CityStreetCreateRequestDto {
    String index;
}
