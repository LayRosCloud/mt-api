package com.trans.api.mapper;

import com.trans.api.dto.bank.BankResponseDto;
import com.trans.api.entity.BankEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BankMapper {
    List<BankResponseDto> toListDto(List<BankEntity> entities);
    BankResponseDto toDto(BankEntity entity);
}
