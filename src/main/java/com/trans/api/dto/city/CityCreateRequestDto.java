package com.trans.api.dto.city;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CityCreateRequestDto {
    String name;
    Integer areaId;
}
