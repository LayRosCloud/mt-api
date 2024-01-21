package com.trans.api.service.impl;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.account.AccountCreateRequestDto;
import com.trans.api.dto.account.AccountResponseDto;
import com.trans.api.dto.account.AccountUpdateRequestDto;
import com.trans.api.entity.AccountEntity;
import com.trans.api.entity.CounterpartyEntity;
import com.trans.api.entity.StockMaterialEntity;
import com.trans.api.mapper.AccountMapper;
import com.trans.api.repository.AccountRepository;
import com.trans.api.repository.CounterpartyRepository;
import com.trans.api.repository.StockMaterialRepository;
import com.trans.api.scripts.helpers.ThrowableHelper;
import com.trans.api.service.AccountService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountServiceImpl implements AccountService {
    AccountRepository accountRepository;
    CounterpartyRepository counterpartyRepository;
    StockMaterialRepository stockMaterialRepository;
    AccountMapper mapper;

    @Override
    public List<AccountResponseDto> findAll() {
        List<AccountEntity> accounts = accountRepository.findAll();
        return mapper.toListDto(accounts);
    }

    @Override
    public AccountResponseDto findById(Long id) {
        AccountEntity account = getAccountOrThrowNotFoundException(id);
        return mapper.toDto(account);
    }

    @Override
    public AccountResponseDto create(AccountCreateRequestDto dto) {
        CounterpartyEntity buyer = counterpartyRepository.findById(dto.getBuyerId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getBuyerId()))
        );

        CounterpartyEntity receiver = counterpartyRepository.findById(dto.getReceiverId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getReceiverId()))
        );

        StockMaterialEntity stockMaterial = stockMaterialRepository.findById(dto.getStockMaterialId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getStockMaterialId()))
        );

        AccountEntity account = AccountEntity.builder()
                .buyer(buyer)
                .receiver(receiver)
                .price(dto.getPrice())
                .discount(dto.getDiscount())
                .number(dto.getNumber())
                .stockMaterial(stockMaterial)
                .number(dto.getNumber())
                .build();

        AccountEntity result = accountRepository.saveAndFlush(account);

        return mapper.toDto(result);
    }

    @Override
    public AccountResponseDto update(AccountUpdateRequestDto dto) {
        AccountEntity account = getAccountOrThrowNotFoundException(dto.getId());

        CounterpartyEntity buyer = counterpartyRepository.findById(dto.getBuyerId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getBuyerId()))
        );

        CounterpartyEntity receiver = counterpartyRepository.findById(dto.getReceiverId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getReceiverId()))
        );

        StockMaterialEntity stockMaterial = stockMaterialRepository.findById(dto.getStockMaterialId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getStockMaterialId()))
        );

        account.setBuyer(buyer);
        account.setReceiver(receiver);

        account.setDiscount(dto.getDiscount());
        account.setCount(dto.getCount());
        account.setPrice(dto.getPrice());

        account.setStockMaterial(stockMaterial);
        account.setNumber(dto.getNumber());

        AccountEntity result = accountRepository.saveAndFlush(account);

        return mapper.toDto(result);
    }

    @Override
    public AckDto delete(Long id) {
        AccountEntity account = getAccountOrThrowNotFoundException(id);

        accountRepository.delete(account);

        return AckDto.builder().answer(true).build();
    }

    private AccountEntity getAccountOrThrowNotFoundException(Long id){
        return accountRepository.findById(id).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(id))
        );
    }
}
