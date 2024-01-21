package com.trans.api.dto.citystreet;

import com.trans.api.entity.CityStreetEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CityStreetUpdateRequestDto {
    Integer id;
    Integer cityId;
    Integer streetId;
    String index;
}
