package com.trans.api.service.impl;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.account.AccountUpdateRequestDto;
import com.trans.api.dto.address.AddressCreateRequestDto;
import com.trans.api.dto.address.AddressResponseDto;
import com.trans.api.dto.address.AddressUpdateRequestDto;
import com.trans.api.entity.AddressEntity;
import com.trans.api.entity.CityStreetEntity;
import com.trans.api.mapper.AddressMapper;
import com.trans.api.repository.AddressRepository;
import com.trans.api.repository.CityStreetRepository;
import com.trans.api.scripts.helpers.ThrowableHelper;
import com.trans.api.service.AddressService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddressServiceImpl implements AddressService {

    AddressRepository addressRepository;
    CityStreetRepository cityStreetRepository;

    AddressMapper mapper;

    @Override
    public List<AddressResponseDto> findAll() {
        List<AddressEntity> addresses = addressRepository.findAll();
        return mapper.toListDto(addresses);
    }

    @Override
    public AddressResponseDto findById(Long id) {
        AddressEntity address = getAddressOrThrowNotFoundException(id);
        return mapper.toDto(address);
    }

    @Override
    @Transactional
    public AddressResponseDto create(AddressCreateRequestDto dto) {
        CityStreetEntity cityStreet = cityStreetRepository.findById(dto.getCityStreetId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getCityStreetId()))
        );
        AddressEntity address = AddressEntity.builder()
                .apartment(dto.getApartment())
                .entrance(dto.getEntrance())
                .corpus(dto.getCorpus())
                .houseNumber(dto.getHouseNumber())
                .cityStreet(cityStreet)
                .build();

        AddressEntity result = addressRepository.saveAndFlush(address);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public AddressResponseDto update(AddressUpdateRequestDto dto) {
        AddressEntity address = getAddressOrThrowNotFoundException(dto.getId());
        CityStreetEntity cityStreet = cityStreetRepository.findById(dto.getCityStreetId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getCityStreetId()))
        );
        address.setCityStreet(cityStreet);
        address.setApartment(dto.getApartment());
        address.setCorpus(dto.getCorpus());
        address.setEntrance(dto.getEntrance());
        address.setHouseNumber(dto.getHouseNumber());

        AddressEntity result = addressRepository.saveAndFlush(address);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public AckDto delete(Long id) {
        AddressEntity address = getAddressOrThrowNotFoundException(id);

        addressRepository.delete(address);

        return AckDto.builder().answer(true).build();
    }

    private AddressEntity getAddressOrThrowNotFoundException(Long id){
        return addressRepository.findById(id).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(id))
        );
    }
}
