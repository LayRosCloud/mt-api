package com.trans.api.service.impl;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.unit.UnitCreateRequestDto;
import com.trans.api.dto.unit.UnitResponseDto;
import com.trans.api.dto.unit.UnitUpdateRequestDto;
import com.trans.api.entity.UnitEntity;
import com.trans.api.mapper.UnitMapper;
import com.trans.api.repository.UnitRepository;
import com.trans.api.scripts.helpers.ThrowableHelper;
import com.trans.api.service.UnitService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UnitServiceImpl implements UnitService {
    UnitRepository repository;
    UnitMapper mapper;

    @Override
    public List<UnitResponseDto> findAll() {
        List<UnitEntity> units = repository.findAll();
        return mapper.toListDto(units);
    }

    @Override
    public UnitResponseDto findById(Short id) {
        UnitEntity unit = getUnitOrThrowNotFoundException(id);
        return mapper.toDto(unit);
    }

    @Override
    public UnitResponseDto create(UnitCreateRequestDto dto) {
        UnitEntity unit = UnitEntity.builder()
                .name(dto.getName())
                .build();
        UnitEntity result = repository.saveAndFlush(unit);
        return mapper.toDto(result);
    }

    @Override
    public UnitResponseDto update(UnitUpdateRequestDto dto) {
        UnitEntity unit = getUnitOrThrowNotFoundException(dto.getId());

        unit.setName(dto.getName());

        UnitEntity result = repository.saveAndFlush(unit);
        return mapper.toDto(result);
    }

    @Override
    public AckDto delete(Short id) {
        UnitEntity unit = getUnitOrThrowNotFoundException(id);

        repository.delete(unit);

        return AckDto.builder()
                .answer(true)
                .build();
    }

    private UnitEntity getUnitOrThrowNotFoundException(Short id){
        return repository.findById(id).orElseThrow(()->
                        ThrowableHelper.throwNotFoundException(String.valueOf(id))
                );
    }
}
