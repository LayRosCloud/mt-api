package com.trans.api.dto.stock;

import com.trans.api.dto.address.AddressResponseDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockResponseDto {
    Short id;
    String name;
    AddressResponseDto address;
}
