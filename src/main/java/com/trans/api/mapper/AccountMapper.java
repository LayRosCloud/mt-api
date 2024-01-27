package com.trans.api.mapper;

import com.trans.api.dto.account.AccountResponseDto;
import com.trans.api.entity.AccountEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CounterpartyMapper.class, StockMaterialMapper.class})
public interface AccountMapper {
    List<AccountResponseDto> toListDto(List<AccountEntity> entities);
    AccountResponseDto toDto(AccountEntity entity);
}
