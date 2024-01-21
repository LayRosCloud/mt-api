package com.trans.api.dto.area;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AreaUpdateRequestDto {
    Integer id;
    String name;
    Integer regionId;
}
