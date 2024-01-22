package com.trans.api.service.impl;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.counterpartytype.CounterpartyTypeCreateRequestDto;
import com.trans.api.dto.counterpartytype.CounterpartyTypeResponseDto;
import com.trans.api.dto.counterpartytype.CounterpartyTypeUpdateRequestDto;
import com.trans.api.entity.CounterpartyTypeEntity;
import com.trans.api.mapper.CounterpartyTypeMapper;
import com.trans.api.repository.CounterpartyTypeRepository;
import com.trans.api.scripts.helpers.ThrowableHelper;
import com.trans.api.service.CounterpartyTypeService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CounterpartyTypeServiceImpl implements CounterpartyTypeService {
    CounterpartyTypeRepository repository;
    CounterpartyTypeMapper mapper;

    @Override
    public List<CounterpartyTypeResponseDto> findAll() {
        List<CounterpartyTypeEntity> types = repository.findAll();
        return mapper.toListDto(types);
    }

    @Override
    public CounterpartyTypeResponseDto findById(Short id) {
        CounterpartyTypeEntity type = getTypeOrThrowNotFoundException(id);
        return mapper.toDto(type);
    }

    @Override
    @Transactional
    public CounterpartyTypeResponseDto create(CounterpartyTypeCreateRequestDto dto) {
        CounterpartyTypeEntity type = CounterpartyTypeEntity.builder()
                .name(dto.getName())
                .build();

        CounterpartyTypeEntity result = repository.saveAndFlush(type);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public CounterpartyTypeResponseDto update(CounterpartyTypeUpdateRequestDto dto) {
        CounterpartyTypeEntity type = getTypeOrThrowNotFoundException(dto.getId());

        type.setName(dto.getName());

        CounterpartyTypeEntity result = repository.saveAndFlush(type);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public AckDto delete(Short id) {
        CounterpartyTypeEntity type = getTypeOrThrowNotFoundException(id);

        repository.saveAndFlush(type);

        return AckDto.builder().build();
    }

    private CounterpartyTypeEntity getTypeOrThrowNotFoundException(Short id){
        return repository.findById(id).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(id))
        );
    }
}
