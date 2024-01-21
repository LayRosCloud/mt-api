package com.trans.api.dto.supply;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplyCreateRequestDto {
    Long counterpartyId;
    Integer stockMaterialId;
    Integer count;
    Double price;
    Double sum;
}
