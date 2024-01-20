package com.trans.api.service.impl;

import com.trans.api.dto.role.RoleCreateRequestDto;
import com.trans.api.dto.role.RoleResponseDto;
import com.trans.api.dto.role.RoleUpdateRequestDto;
import com.trans.api.entity.RoleEntity;
import com.trans.api.mapper.RoleMapper;
import com.trans.api.repository.RoleRepository;
import com.trans.api.scripts.helpers.ThrowableHelper;
import com.trans.api.service.RoleService;
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
        RoleEntity role = find(id);
        return mapper.toDto(role);
    }

    @Override
    public RoleResponseDto create(RoleCreateRequestDto dto) {
        RoleEntity role = new RoleEntity();
        role.setName(dto.getName());
        RoleEntity result = repository.save(role);
        return mapper.toDto(result);
    }

    @Override
    public RoleResponseDto update(RoleUpdateRequestDto dto) {
        RoleEntity role = find(dto.getId());

        role.setName(dto.getName());
        RoleEntity result = repository.save(role);

        return mapper.toDto(result);
    }

    @Override
    public RoleResponseDto delete(Short id) {
        RoleEntity role = find(id);
        repository.delete(role);
        return mapper.toDto(role);
    }

    private RoleEntity find(Short id){
        RoleEntity role = repository.findById(id).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(Long.valueOf(id))
        );
        return role;
    }
}
