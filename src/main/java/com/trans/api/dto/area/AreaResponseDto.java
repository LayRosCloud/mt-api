package com.trans.api.dto.area;

import com.trans.api.dto.region.RegionResponseDto;
import com.trans.api.entity.RegionEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AreaResponseDto {
    Integer id;
    String name;
    RegionResponseDto region;
}
