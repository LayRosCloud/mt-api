package com.trans.api.mapper;

import com.trans.api.dto.counterparty.CounterpartyResponseDto;
import com.trans.api.dto.counterpartytype.CounterpartyTypeResponseDto;
import com.trans.api.entity.CounterpartyTypeEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CounterpartyTypeMapper {
    List<CounterpartyTypeResponseDto> toListDto(List<CounterpartyTypeEntity> entities);
    CounterpartyTypeResponseDto toDto(CounterpartyTypeEntity entity);
}
