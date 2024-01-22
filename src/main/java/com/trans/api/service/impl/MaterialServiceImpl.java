package com.trans.api.service.impl;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.material.MaterialCreateRequestDto;
import com.trans.api.dto.material.MaterialResponseDto;
import com.trans.api.dto.material.MaterialUpdateRequestDto;
import com.trans.api.entity.MaterialEntity;
import com.trans.api.entity.UnitEntity;
import com.trans.api.mapper.MaterialMapper;
import com.trans.api.repository.MaterialRepository;
import com.trans.api.repository.UnitRepository;
import com.trans.api.scripts.helpers.ThrowableHelper;
import com.trans.api.service.MaterialService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MaterialServiceImpl implements MaterialService {
    MaterialRepository materialRepository;
    UnitRepository unitRepository;

    MaterialMapper mapper;

    @Override
    public List<MaterialResponseDto> findAll() {
        List<MaterialEntity> materials = materialRepository.findAll();
        return mapper.toListDto(materials);
    }

    @Override
    public MaterialResponseDto findById(Integer id) {
        MaterialEntity material = getMaterialOrThrowNotFoundException(id);
        return mapper.toDto(material);
    }

    @Override
    @Transactional
    public MaterialResponseDto create(MaterialCreateRequestDto dto) {
        UnitEntity unit = unitRepository.findById(dto.getUnitId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getUnitId()))
        );

        MaterialEntity material = MaterialEntity.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .width(dto.getWidth())
                .height(dto.getHeight())
                .depth(dto.getDepth())
                .unit(unit)
                .build();

        MaterialEntity result = materialRepository.saveAndFlush(material);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public MaterialResponseDto update(MaterialUpdateRequestDto dto) {
        MaterialEntity material = getMaterialOrThrowNotFoundException(dto.getId());

        UnitEntity unit = unitRepository.findById(dto.getUnitId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getUnitId()))
        );

        material.setDepth(dto.getDepth());
        material.setWidth(dto.getWidth());
        material.setHeight(dto.getHeight());

        material.setName(dto.getName());
        material.setPrice(dto.getPrice());

        material.setUnit(unit);

        MaterialEntity result = materialRepository.saveAndFlush(material);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public AckDto delete(Integer id) {
        MaterialEntity material = getMaterialOrThrowNotFoundException(id);

        materialRepository.delete(material);

        return AckDto.builder().answer(true).build();
    }

    private MaterialEntity getMaterialOrThrowNotFoundException(Integer id){
        return materialRepository.findById(id).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(id))
        );
    }
}
