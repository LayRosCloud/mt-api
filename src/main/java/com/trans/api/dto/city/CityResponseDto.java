package com.trans.api.dto.city;

import com.trans.api.dto.area.AreaResponseDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.awt.geom.Area;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CityResponseDto {
    Integer id;
    String name;
    AreaResponseDto area;
}
