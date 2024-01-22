package com.trans.api.dto.stockmaterial;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockMaterialCreateRequestDto {
    Integer count;
}
