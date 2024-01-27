package com.trans.api.service.impl;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.street.StreetCreateDto;
import com.trans.api.dto.street.StreetResponseDto;
import com.trans.api.dto.street.StreetUpdateDto;
import com.trans.api.entity.StreetEntity;
import com.trans.api.mapper.StreetMapper;
import com.trans.api.repository.StreetRepository;
import com.trans.api.scripts.helpers.ThrowableHelper;
import com.trans.api.service.StreetService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StreetServiceImpl implements StreetService {
    StreetRepository repository;
    StreetMapper mapper;

    @Override
    public List<StreetResponseDto> findAll() {
        List<StreetEntity> streets = repository.findAll();
        return mapper.toListDto(streets);
    }

    @Override
    public StreetResponseDto findById(Integer id) {
        StreetEntity street = getStreetOrThrowException(id);
        return mapper.toDto(street);
    }

    @Override
    @Transactional
    public StreetResponseDto create(StreetCreateDto dto) {
        StreetEntity street = StreetEntity.builder()
                .name(dto.getName())
                .build();
        StreetEntity result = repository.saveAndFlush(street);
        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public StreetResponseDto update(StreetUpdateDto dto) {
        StreetEntity street = getStreetOrThrowException(dto.getId());

        street.setName(dto.getName());

        StreetEntity result = repository.saveAndFlush(street);
        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public AckDto delete(Integer id) {
        StreetEntity street = getStreetOrThrowException(id);

        repository.delete(street);

        return AckDto.builder()
                .answer(true)
                .build();
    }

    private StreetEntity getStreetOrThrowException(Integer id){
        return repository.findById(id).orElseThrow(()->
                        ThrowableHelper.throwNotFoundException(String.valueOf(id))
                );
    }
}
