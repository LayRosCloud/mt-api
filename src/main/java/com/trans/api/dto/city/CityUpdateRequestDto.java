package com.trans.api.dto.city;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.awt.geom.Area;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CityUpdateRequestDto {
    Integer id;
    String name;
    Integer areaId;
}
