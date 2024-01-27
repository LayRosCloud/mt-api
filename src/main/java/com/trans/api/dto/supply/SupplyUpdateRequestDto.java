package com.trans.api.dto.supply;

import com.trans.api.dto.counterparty.CounterpartyResponseDto;
import com.trans.api.dto.stockmaterial.StockMaterialResponseDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplyUpdateRequestDto {
    Long id;
    Long counterpartyId;
    Integer stockMaterialId;
    Integer count;
    Double price;
    Double sum;
}
