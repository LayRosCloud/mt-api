package com.trans.api.dto.stock;

import com.trans.api.dto.address.AddressResponseDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockCreateRequestDto {
    String name;
    Long addressId;
}
