package com.trans.api.service.impl;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.user.UserCreateRequestDto;
import com.trans.api.dto.user.UserResponseDto;
import com.trans.api.dto.user.UserUpdateRequestDto;
import com.trans.api.entity.RoleEntity;
import com.trans.api.entity.UserEntity;
import com.trans.api.mapper.UserMapper;
import com.trans.api.repository.RoleRepository;
import com.trans.api.repository.UserRepository;
import com.trans.api.scripts.helpers.ThrowableHelper;
import com.trans.api.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper mapper;

    @Override
    public List<UserResponseDto> findAll() {
        List<UserEntity> entities = userRepository.findAll();
        return mapper.toListDto(entities);
    }

    @Override
    public UserResponseDto findById(Long id) {
        UserEntity user = getUserOrThrowNotFoundException(id);
        return mapper.toDto(user);
    }

    @Override
    @Transactional
    public UserResponseDto create(UserCreateRequestDto dto) {
        if(userRepository.existsByLogin(dto.getLogin())){
            throw ThrowableHelper.throwBadRequestException("Exception! Login \"%s\" exists", dto.getLogin());
        }

        RoleEntity role = roleRepository.findById(dto.getRoleId()).orElseThrow(()->
                    ThrowableHelper.throwNotFoundException(String.valueOf(dto.getRoleId()))
                );

        UserEntity user = UserEntity.builder()
                .login(dto.getLogin())
                .password(dto.getPassword())
                .role(role)
                .build();

        UserEntity response = userRepository.saveAndFlush(user);

        return mapper.toDto(response);
    }

    @Override
    @Transactional
    public UserResponseDto update(UserUpdateRequestDto dto) {
        UserEntity user = getUserOrThrowNotFoundException(dto.getId());

        if(!user.getLogin().equalsIgnoreCase(dto.getLogin()) && userRepository.existsByLogin(dto.getLogin())){
            throw ThrowableHelper.throwBadRequestException("Exception! Login \"%s\" exists", dto.getLogin());
        }

        RoleEntity role = roleRepository.findById(dto.getRoleId()).orElseThrow(()->
                ThrowableHelper.throwNotFoundException(String.valueOf(dto.getRoleId()))
        );
        user.setLogin(dto.getLogin());
        user.setPassword(dto.getPassword());
        user.setRole(role);

        UserEntity result = userRepository.saveAndFlush(user);

        return mapper.toDto(result);
    }

    @Override
    @Transactional
    public AckDto delete(Long id) {
        UserEntity user = getUserOrThrowNotFoundException(id);

        userRepository.delete(user);

        return AckDto.builder()
                .answer(true)
                .build();
    }

    private UserEntity getUserOrThrowNotFoundException(Long id){
        return userRepository.findById(id).orElseThrow(()->
                        ThrowableHelper.throwNotFoundException(String.valueOf(id))
                );
    }
}
