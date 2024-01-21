package com.trans.api.dto.supply;

import com.trans.api.dto.counterparty.CounterpartyResponseDto;
import com.trans.api.dto.stockmaterial.StockMaterialResponseDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplyResponseDto {
    Long id;
    CounterpartyResponseDto counterparty;
    StockMaterialResponseDto stockMaterial;
    Integer count;
    Double price;
    Double sum;
}
