package com.trans.api.dto.material;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MaterialUpdateRequestDto {
    Integer id;
    String name;
    Double price;
    Double width;
    Double height;
    Double depth;
    Short unitId;
}
