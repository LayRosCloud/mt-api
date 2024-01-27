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
    public CityStreetResponseDto create(Integer cityId, Integer streetId, CityStreetCreateRequestDto dto) {
        CityEntity city = cityRepository.findById(cityId).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(cityId))
        );

        StreetEntity street = streetRepository.findById(streetId).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(streetId))
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
    public CityStreetResponseDto update(Integer cityId, Integer streetId, CityStreetUpdateRequestDto dto) {
        CityStreetEntity cityStreet = cityStreetRepository.findById(dto.getId()).orElseThrow(()->
                    ThrowableHelper.throwNotFoundException(String.valueOf(dto.getId()))
                );

        CityEntity city = cityRepository.findById(cityId).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(cityId))
        );

        StreetEntity street = streetRepository.findById(streetId).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(streetId))
        );

        cityStreet.setCity(city);
        cityStreet.setStreet(street);
        cityStreet.setIndex(dto.getIndex());

        CityStreetEntity result = cityStreetRepository.saveAndFlush(cityStreet);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public AckDto delete(Integer cityId, Integer streetId) {
        CityStreetEntity cityStreet = getCityStreetOrThrowNotFoundException(cityId, streetId);

        cityStreetRepository.delete(cityStreet);

        return AckDto.builder().answer(true).build();
    }

    private CityStreetEntity getCityStreetOrThrowNotFoundException(Integer cityId, Integer streetId){
        return cityStreetRepository.findByCityIdAndStreetId(cityId, streetId).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(cityId + " " + streetId)
        );
    }
}
