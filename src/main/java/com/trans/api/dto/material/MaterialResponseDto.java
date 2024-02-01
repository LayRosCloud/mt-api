package com.trans.api.dto.material;

import com.trans.api.dto.unit.UnitResponseDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MaterialResponseDto {
    Integer id;
    String name;
    Double price;
    Double width;
    Double height;
    Double depth;
    UnitResponseDto unit;
}
