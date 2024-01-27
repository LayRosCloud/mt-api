package com.trans.api.service.impl;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.role.RoleCreateRequestDto;
import com.trans.api.dto.role.RoleResponseDto;
import com.trans.api.dto.role.RoleUpdateRequestDto;
import com.trans.api.entity.RoleEntity;
import com.trans.api.mapper.RoleMapper;
import com.trans.api.repository.RoleRepository;
import com.trans.api.scripts.helpers.ThrowableHelper;
import com.trans.api.service.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;
    private final RoleMapper mapper;

    @Override
    public List<RoleResponseDto> findAll() {
        List<RoleEntity> roles = repository.findAll();
        return mapper.toListDto(roles);
    }

    @Override
    public RoleResponseDto findById(Short id) {
        RoleEntity role = getRoleOrThrowNotFoundException(id);
        return mapper.toDto(role);
    }

    @Override
    @Transactional
    public RoleResponseDto create(RoleCreateRequestDto dto) {
        RoleEntity role = RoleEntity.builder()
                        .name(dto.getName())
                        .build();

        RoleEntity result = repository.saveAndFlush(role);
        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public RoleResponseDto update(RoleUpdateRequestDto dto) {
        RoleEntity role = getRoleOrThrowNotFoundException(dto.getId());

        role.setName(dto.getName());
        RoleEntity result = repository.saveAndFlush(role);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public AckDto delete(Short id) {
        RoleEntity role = getRoleOrThrowNotFoundException(id);

        repository.delete(role);

        return AckDto.builder()
                .answer(true)
                .build();
    }

    private RoleEntity getRoleOrThrowNotFoundException(Short id){
        return repository.findById(id).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(id))
        );
    }
}
