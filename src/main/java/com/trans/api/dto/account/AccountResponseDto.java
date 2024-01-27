package com.trans.api.dto.account;

import com.trans.api.dto.counterparty.CounterpartyResponseDto;
import com.trans.api.dto.stockmaterial.StockMaterialResponseDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountResponseDto {
    Long id;
    Integer count;
    Double price;
    Double discount;
    Long number;
    CounterpartyResponseDto buyer;
    CounterpartyResponseDto receiver;
    StockMaterialResponseDto stockMaterial;
}
