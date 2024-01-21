package com.trans.api.dto.address;

import com.trans.api.entity.CityStreetEntity;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressResponseDto {
    Long id;
    String houseNumber;
    String corpus;
    String entrance;
    String apartment;
    CityStreetEntity cityStreet;
}
