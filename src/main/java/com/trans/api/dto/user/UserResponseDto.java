package com.trans.api.dto.user;

import com.trans.api.dto.role.RoleResponseDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.management.relation.Role;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponseDto {
    private Long id;
    private String login;
    private RoleResponseDto role;
}
