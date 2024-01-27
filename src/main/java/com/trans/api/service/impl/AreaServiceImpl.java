package com.trans.api.service.impl;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.area.AreaCreateRequestDto;
import com.trans.api.dto.area.AreaResponseDto;
import com.trans.api.dto.area.AreaUpdateRequestDto;
import com.trans.api.entity.AreaEntity;
import com.trans.api.entity.RegionEntity;
import com.trans.api.mapper.AreaMapper;
import com.trans.api.repository.AreaRepository;
import com.trans.api.repository.RegionRepository;
import com.trans.api.scripts.helpers.ThrowableHelper;
import com.trans.api.service.AreaService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.awt.geom.Area;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AreaServiceImpl implements AreaService {
    AreaRepository areaRepository;
    RegionRepository regionRepository;

    AreaMapper mapper;

    @Override
    public List<AreaResponseDto> findAll() {
        List<AreaEntity> areas = areaRepository.findAll();
        return mapper.toListDto(areas);
    }

    @Override
    public AreaResponseDto findById(Integer id) {
        AreaEntity area = getAreaOrThrowNotFoundException(id);
        return mapper.toDto(area);
    }

    @Override
    @Transactional
    public AreaResponseDto create(AreaCreateRequestDto dto) {
        RegionEntity region = regionRepository.findById(dto.getRegionId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getRegionId()))
        );
        AreaEntity area = AreaEntity.builder()
                .name(dto.getName())
                .region(region)
                .build();

        AreaEntity result = areaRepository.saveAndFlush(area);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public AreaResponseDto update(AreaUpdateRequestDto dto) {
        AreaEntity area = getAreaOrThrowNotFoundException(dto.getId());

        RegionEntity region = regionRepository.findById(dto.getRegionId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getRegionId()))
        );

        area.setName(dto.getName());
        area.setRegion(region);

        AreaEntity result = areaRepository.saveAndFlush(area);
        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public AckDto delete(Integer id) {
        AreaEntity area = getAreaOrThrowNotFoundException(id);

        areaRepository.delete(area);

        return AckDto.builder().answer(true).build();
    }

    private AreaEntity getAreaOrThrowNotFoundException(Integer id){
        return areaRepository.findById(id).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(id))
        );
    }
}
