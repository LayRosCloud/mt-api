package com.trans.api.dto.citystreet;

import com.trans.api.dto.city.CityResponseDto;
import com.trans.api.dto.street.StreetResponseDto;
import com.trans.api.entity.CityStreetEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CityStreetResponseDto {
    Integer id;
    CityResponseDto city;
    StreetResponseDto street;
    String index;
}
