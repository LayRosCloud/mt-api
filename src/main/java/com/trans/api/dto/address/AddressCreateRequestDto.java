package com.trans.api.dto.address;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressCreateRequestDto {
    String houseNumber;
    String corpus;
    String entrance;
    String apartment;
    Integer cityStreetId;
}
