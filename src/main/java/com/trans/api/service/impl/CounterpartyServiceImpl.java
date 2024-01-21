package com.trans.api.service.impl;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.counterparty.CounterpartyCreateRequestDto;
import com.trans.api.dto.counterparty.CounterpartyResponseDto;
import com.trans.api.dto.counterparty.CounterpartyUpdateRequestDto;
import com.trans.api.entity.AddressEntity;
import com.trans.api.entity.BankEntity;
import com.trans.api.entity.CounterpartyEntity;
import com.trans.api.entity.CounterpartyTypeEntity;
import com.trans.api.mapper.CounterpartyMapper;
import com.trans.api.repository.AddressRepository;
import com.trans.api.repository.BankRepository;
import com.trans.api.repository.CounterpartyRepository;
import com.trans.api.repository.CounterpartyTypeRepository;
import com.trans.api.scripts.helpers.ThrowableHelper;
import com.trans.api.service.CounterpartyService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CounterpartyServiceImpl implements CounterpartyService {
    CounterpartyRepository counterpartyRepository;
    AddressRepository addressRepository;
    BankRepository bankRepository;
    CounterpartyTypeRepository typeRepository;

    CounterpartyMapper mapper;

    @Override
    public List<CounterpartyResponseDto> findAll() {
        List<CounterpartyEntity> counterparties = counterpartyRepository.findAll();
        return mapper.toListDto(counterparties);
    }

    @Override
    public CounterpartyResponseDto findById(Long id) {
        CounterpartyEntity counterparty = getCounterpartyOrThrowNotFoundException(id);
        return mapper.toDto(counterparty);
    }

    @Override
    public CounterpartyResponseDto create(CounterpartyCreateRequestDto dto) {
        AddressEntity address = addressRepository.findById(dto.getAddressId()).orElseThrow(() ->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getAddressId()))
        );

        BankEntity bank = bankRepository.findById(dto.getBankId()).orElseThrow(()->
                      ThrowableHelper.throwNotFoundException(String.valueOf(dto.getBankId()))
        );

        CounterpartyTypeEntity type = typeRepository.findById(dto.getTypeId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getTypeId()))
        );

        CounterpartyEntity counterparty = CounterpartyEntity.builder()
                .address(address)

                .inn(dto.getInn())
                .ogrn(dto.getOgrn())
                .kpp(dto.getKpp())
                .bik(dto.getBik())

                .bank(bank)
                .correspondentAccount(dto.getCorrespondentAccount())

                .phone(dto.getPhone())
                .email(dto.getEmail())

                .type(type)
                .build();

        CounterpartyEntity result = counterpartyRepository.saveAndFlush(counterparty);

        return mapper.toDto(result);
    }

    @Override
    public CounterpartyResponseDto update(CounterpartyUpdateRequestDto dto) {
        CounterpartyEntity counterparty = getCounterpartyOrThrowNotFoundException(dto.getId());

        AddressEntity address = addressRepository.findById(dto.getAddressId()).orElseThrow(() ->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getAddressId()))
        );

        BankEntity bank = bankRepository.findById(dto.getBankId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getBankId()))
        );

        CounterpartyTypeEntity type = typeRepository.findById(dto.getTypeId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getTypeId()))
        );

        counterparty.setAddress(address);

        counterparty.setBik(dto.getBik());
        counterparty.setInn(dto.getInn());
        counterparty.setOgrn(dto.getOgrn());
        counterparty.setKpp(dto.getKpp());

        counterparty.setBank(bank);
        counterparty.setCorrespondentAccount(dto.getCorrespondentAccount());

        counterparty.setPhone(dto.getPhone());
        counterparty.setEmail(dto.getEmail());

        counterparty.setType(type);

        CounterpartyEntity result = counterpartyRepository.saveAndFlush(counterparty);

        return mapper.toDto(result);
    }

    @Override
    public AckDto delete(Long id) {
        CounterpartyEntity counterparty = getCounterpartyOrThrowNotFoundException(id);

        counterpartyRepository.delete(counterparty);

        return AckDto.builder()
                .answer(true)
                .build();
    }

    private CounterpartyEntity getCounterpartyOrThrowNotFoundException(Long id){
        return counterpartyRepository.findById(id).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(id))
        );
    }
}
