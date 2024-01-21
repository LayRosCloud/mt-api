package com.trans.api.dto.stockmaterial;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockMaterialUpdateRequestDto {
    Integer id;
    Short stockId;
    Integer materialId;
    Integer count;
}
