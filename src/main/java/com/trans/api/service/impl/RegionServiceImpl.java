package com.trans.api.service.impl;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.region.RegionCreateRequestDto;
import com.trans.api.dto.region.RegionResponseDto;
import com.trans.api.dto.region.RegionUpdateRequestDto;
import com.trans.api.entity.CountryEntity;
import com.trans.api.entity.RegionEntity;
import com.trans.api.mapper.RegionMapper;
import com.trans.api.repository.CountryRepository;
import com.trans.api.repository.RegionRepository;
import com.trans.api.scripts.helpers.ThrowableHelper;
import com.trans.api.service.RegionService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RegionServiceImpl implements RegionService {
    RegionRepository regionRepository;
    CountryRepository countryRepository;

    RegionMapper mapper;

    @Override
    public List<RegionResponseDto> findAll() {
        List<RegionEntity> regions = regionRepository.findAll();
        return mapper.toListDto(regions);
    }

    @Override
    public RegionResponseDto findById(Integer id) {
        RegionEntity region = getRegionOrThrowNotFound(id);
        return mapper.toDto(region);
    }

    @Override
    @Transactional
    public RegionResponseDto create(RegionCreateRequestDto dto) {
        CountryEntity country = countryRepository.findById(dto.getCountryId()).orElseThrow(() ->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getCountryId()))
        );

        RegionEntity region = RegionEntity.builder()
                .country(country)
                .name(dto.getName())
                .build();

        RegionEntity result = regionRepository.saveAndFlush(region);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public RegionResponseDto update(RegionUpdateRequestDto dto) {
        RegionEntity region = getRegionOrThrowNotFound(dto.getId());
        CountryEntity country = countryRepository.findById(dto.getCountryId()).orElseThrow(() ->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getCountryId()))
        );
        region.setCountry(country);
        region.setName(dto.getName());

        RegionEntity result = regionRepository.saveAndFlush(region);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public AckDto delete(Integer id) {
        RegionEntity region = getRegionOrThrowNotFound(id);

        regionRepository.delete(region);

        return AckDto.builder().answer(true).build();
    }

    private RegionEntity getRegionOrThrowNotFound(Integer id){
        return regionRepository.findById(id).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(id))
        );
    }
}
