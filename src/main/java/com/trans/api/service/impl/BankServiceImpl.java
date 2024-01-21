package com.trans.api.service.impl;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.bank.BankCreateRequestDto;
import com.trans.api.dto.bank.BankResponseDto;
import com.trans.api.dto.bank.BankUpdateRequestDto;
import com.trans.api.entity.BankEntity;
import com.trans.api.mapper.BankMapper;
import com.trans.api.repository.BankRepository;
import com.trans.api.scripts.helpers.ThrowableHelper;
import com.trans.api.service.BankService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BankServiceImpl implements BankService {
    BankRepository repository;
    BankMapper mapper;

    @Override
    public List<BankResponseDto> findAll() {
        List<BankEntity> banks = repository.findAll();
        return mapper.toListDto(banks);
    }

    @Override
    public BankResponseDto findById(Short id) {
        BankEntity bank = getBankOrThrowNotFoundException(id);
        return mapper.toDto(bank);
    }

    @Override
    @Transactional
    public BankResponseDto create(BankCreateRequestDto dto) {
        BankEntity bank = BankEntity.builder()
                .name(dto.getName())
                .build();

        BankEntity result = repository.saveAndFlush(bank);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public BankResponseDto update(BankUpdateRequestDto dto) {
        BankEntity bank = getBankOrThrowNotFoundException(dto.getId());

        bank.setName(dto.getName());

        BankEntity result = repository.saveAndFlush(bank);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public AckDto delete(Short id) {
        BankEntity bank = getBankOrThrowNotFoundException(id);

        repository.delete(bank);

        return AckDto.builder().answer(true).build();
    }

    private BankEntity getBankOrThrowNotFoundException(Short id){
        return repository.findById(id).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(id))
        );
    }
}
