package com.trans.api.dto.stockmaterial;

import com.trans.api.dto.material.MaterialResponseDto;
import com.trans.api.dto.stock.StockResponseDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockMaterialResponseDto {
    Integer id;
    StockResponseDto stock;
    MaterialResponseDto material;
    Integer count;
}
