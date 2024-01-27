package com.trans.api.dto.account;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountCreateRequestDto {
    Integer count;
    Double price;
    Double discount;
    Long number;
    Long buyerId;
    Long receiverId;
    Integer stockMaterialId;
}
