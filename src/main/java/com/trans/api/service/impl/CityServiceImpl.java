package com.trans.api.service.impl;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.city.CityCreateRequestDto;
import com.trans.api.dto.city.CityResponseDto;
import com.trans.api.dto.city.CityUpdateRequestDto;
import com.trans.api.entity.AreaEntity;
import com.trans.api.entity.CityEntity;
import com.trans.api.mapper.CityMapper;
import com.trans.api.repository.AreaRepository;
import com.trans.api.repository.CityRepository;
import com.trans.api.scripts.helpers.ThrowableHelper;
import com.trans.api.service.CityService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CityServiceImpl implements CityService {
    CityRepository cityRepository;
    AreaRepository areaRepository;

    CityMapper mapper;
    @Override
    public List<CityResponseDto> findAll() {
        List<CityEntity> cities = cityRepository.findAll();

        return mapper.toListDto(cities);
    }

    @Override
    public CityResponseDto findById(Integer id) {
        CityEntity city = getCityOrThrowNotFoundException(id);
        return mapper.toDto(city);
    }

    @Override
    @Transactional
    public CityResponseDto create(CityCreateRequestDto dto) {
        AreaEntity area = areaRepository.findById(dto.getAreaId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getAreaId()))
        );

        CityEntity city = CityEntity.builder()
                .name(dto.getName())
                .area(area)
                .build();

        CityEntity result = cityRepository.saveAndFlush(city);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public CityResponseDto update(CityUpdateRequestDto dto) {
        CityEntity city = getCityOrThrowNotFoundException(dto.getId());

        AreaEntity area = areaRepository.findById(dto.getAreaId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getAreaId()))
        );

        city.setArea(area);
        city.setName(dto.getName());

        CityEntity result = cityRepository.saveAndFlush(city);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public AckDto delete(Integer id) {
        CityEntity city = getCityOrThrowNotFoundException(id);

        cityRepository.delete(city);

        return AckDto.builder().answer(true).build();
    }

    private CityEntity getCityOrThrowNotFoundException(Integer id){
        return cityRepository.findById(id).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(id))
        );
    }
}
