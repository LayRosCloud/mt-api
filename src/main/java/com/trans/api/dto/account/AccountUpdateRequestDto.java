package com.trans.api.dto.account;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountUpdateRequestDto {
    Long id;
    Integer count;
    Double price;
    Double discount;
    Long number;
    Long buyerId;
    Long receiverId;
    Integer stockMaterialId;
}
