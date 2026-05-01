package com.raihan.springsecurity.services;

import com.raihan.springsecurity.common.GenericResponse;
import com.raihan.springsecurity.model.UserDto;
import com.raihan.springsecurity.model.UsersInfoResponse;

public interface UsersService {
    UsersInfoResponse getAllUserInfo();

    String saveUserInfo(UserDto userDto);

    GenericResponse updateUser(UserDto user);
}
