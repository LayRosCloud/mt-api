package com.trans.api.dto.region;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegionCreateRequestDto {
    String name;
    Short countryId;
}
