package com.trans.api.mapper;

import com.trans.api.dto.counterparty.CounterpartyResponseDto;
import com.trans.api.entity.CounterpartyEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BankMapper.class, CounterpartyTypeMapper.class, AddressMapper.class})
public interface CounterpartyMapper {
    List<CounterpartyResponseDto> toListDto(List<CounterpartyEntity> entities);
    CounterpartyResponseDto toDto(CounterpartyEntity entity);
}
