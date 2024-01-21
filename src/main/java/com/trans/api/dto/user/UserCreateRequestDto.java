package com.trans.api.dto.user;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreateRequestDto {
    private String login;
    private String password;
    private Short roleId;
}
