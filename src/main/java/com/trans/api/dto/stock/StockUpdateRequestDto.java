package com.trans.api.dto.stock;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockUpdateRequestDto {
    Short id;
    String name;
    Long addressId;
}
