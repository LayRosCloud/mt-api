package com.trans.api.service.impl;

import com.trans.api.dto.AckDto;
import com.trans.api.dto.user.UserCreateRequestDto;
import com.trans.api.dto.user.UserLoginDto;
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

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {

    private static final String key = "aesEncryptionKey";
    private static final String initVector = "encryptionIntVec";

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
        dto.setPassword(hash(dto.getPassword()));
        UserEntity user = UserEntity.builder()
                .login(dto.getLogin().toLowerCase())
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
        user.setLogin(dto.getLogin().toLowerCase());
        String password = dto.getPassword();
        user.setPassword(hash(user.getPassword()));
        user.setPassword(password);
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

    @Override
    public UserResponseDto login(UserLoginDto loginDto) {
        UserEntity user = userRepository.findByLogin(loginDto.getLogin().toLowerCase()).orElseThrow(()->
                ThrowableHelper.throwBadRequestException(String.format("Error! User not founded with login %s", loginDto.getLogin()))
        );
        String decrypt = decrypt(user.getPassword());
        assert decrypt != null;
        if(!decrypt.equals(loginDto.getPassword())){
            throw  ThrowableHelper.throwBadRequestException("Error! User with password is invalid");
        }

        return mapper.toDto(user);
    }

    private UserEntity getUserOrThrowNotFoundException(Long id){
        return userRepository.findById(id).orElseThrow(()->
                        ThrowableHelper.throwNotFoundException(String.valueOf(id))
                );
    }

    private String decrypt(String encrypted) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private String hash(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
