package com.trans.api.dto.region;

import com.trans.api.dto.country.CountryResponseDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegionResponseDto {
    Integer id;
    String name;
    CountryResponseDto country;
}
