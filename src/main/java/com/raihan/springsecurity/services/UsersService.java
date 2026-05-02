package com.raihan.springsecurity.services;

import com.raihan.springsecurity.common.GenericResponse;
import com.raihan.springsecurity.model.UserDto;
import com.raihan.springsecurity.model.UsersInfoResponse;

public interface UsersService {
    UsersInfoResponse getAllUserInfo();

    GenericResponse saveUserInfo(UserDto userDto);

    GenericResponse updateUser(UserDto user);

    GenericResponse deleteUser(Integer id);
}
