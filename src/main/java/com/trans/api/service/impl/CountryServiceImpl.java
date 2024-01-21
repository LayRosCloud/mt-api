package com.trans.api.service.impl;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.country.CountryCreateRequestDto;
import com.trans.api.dto.country.CountryResponseDto;
import com.trans.api.dto.country.CountryUpdateRequestDto;
import com.trans.api.entity.CountryEntity;
import com.trans.api.mapper.CountryMapper;
import com.trans.api.repository.CountryRepository;
import com.trans.api.scripts.helpers.ThrowableHelper;
import com.trans.api.service.CountryService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CountryServiceImpl implements CountryService {

    CountryRepository repository;
    CountryMapper mapper;

    @Override
    public List<CountryResponseDto> findAll() {
        List<CountryEntity> countries = repository.findAll();
        return mapper.toListDto(countries);
    }

    @Override
    public CountryResponseDto findById(Short id) {
        CountryEntity country = getCountryOrThrowNotFoundException(id);
        return mapper.toDto(country);
    }

    @Override
    @Transactional
    public CountryResponseDto create(CountryCreateRequestDto dto) {
        CountryEntity country = CountryEntity.builder()
                .name(dto.getName())
                .build();

        CountryEntity result = repository.saveAndFlush(country);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public CountryResponseDto update(CountryUpdateRequestDto dto) {
        CountryEntity country = getCountryOrThrowNotFoundException(dto.getId());

        country.setName(country.getName());

        CountryEntity result = repository.saveAndFlush(country);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public AckDto delete(Short id) {
        CountryEntity country = getCountryOrThrowNotFoundException(id);

        repository.delete(country);

        return AckDto.builder()
                .answer(true)
                .build();
    }

    private CountryEntity getCountryOrThrowNotFoundException(Short id){
        return repository.findById(id).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(id))
        );
    }
}
