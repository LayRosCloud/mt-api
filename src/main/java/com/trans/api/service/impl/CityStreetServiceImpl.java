package com.trans.api.service.impl;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.citystreet.CityStreetCreateRequestDto;
import com.trans.api.dto.citystreet.CityStreetResponseDto;
import com.trans.api.dto.citystreet.CityStreetUpdateRequestDto;
import com.trans.api.entity.CityEntity;
import com.trans.api.entity.CityStreetEntity;
import com.trans.api.entity.StreetEntity;
import com.trans.api.mapper.CityStreetMapper;
import com.trans.api.repository.CityRepository;
import com.trans.api.repository.CityStreetRepository;
import com.trans.api.repository.StreetRepository;
import com.trans.api.scripts.helpers.ThrowableHelper;
import com.trans.api.service.CityStreetService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CityStreetServiceImpl implements CityStreetService {
    CityRepository cityRepository;
    StreetRepository streetRepository;
    CityStreetRepository cityStreetRepository;

    CityStreetMapper mapper;

    @Override
    public List<CityStreetResponseDto> findAll(Integer cityId) {
        List<CityStreetEntity> cityStreet = cityStreetRepository.findAllByCityId(cityId);
        return mapper.toListDto(cityStreet);
    }

    @Override
    public CityStreetResponseDto findByCityIdAndStreetId(Integer cityId, Integer streetId) {
        CityStreetEntity cityStreet = cityStreetRepository.findByCityIdAndStreetId(cityId, streetId).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(cityId + " " + streetId)
        );
        return mapper.toDto(cityStreet);
    }

    @Override
    @Transactional
    public CityStreetResponseDto create(CityStreetCreateRequestDto dto) {
        CityEntity city = cityRepository.findById(dto.getCityId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getCityId()))
        );

        StreetEntity street = streetRepository.findById(dto.getStreetId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getStreetId()))
        );

        CityStreetEntity cityStreet = CityStreetEntity.builder()
                .city(city)
                .street(street)
                .index(dto.getIndex())
                .build();

        CityStreetEntity result = cityStreetRepository.saveAndFlush(cityStreet);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public CityStreetResponseDto update(CityStreetUpdateRequestDto dto) {
        CityStreetEntity cityStreet = getCityStreetOrThrowNotFoundException(dto.getId());

        CityEntity city = cityRepository.findById(dto.getCityId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getCityId()))
        );

        StreetEntity street = streetRepository.findById(dto.getStreetId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getStreetId()))
        );

        cityStreet.setCity(city);
        cityStreet.setStreet(street);
        cityStreet.setIndex(dto.getIndex());

        CityStreetEntity result = cityStreetRepository.saveAndFlush(cityStreet);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public AckDto delete(Integer id) {
        CityStreetEntity cityStreet = getCityStreetOrThrowNotFoundException(id);

        cityStreetRepository.delete(cityStreet);

        return AckDto.builder().answer(true).build();
    }

    private CityStreetEntity getCityStreetOrThrowNotFoundException(Integer id){
        return cityStreetRepository.findById(id).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(id))
        );
    }
}
