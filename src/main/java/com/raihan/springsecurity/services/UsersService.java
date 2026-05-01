package com.raihan.springsecurity.services;

import com.raihan.springsecurity.model.UserDto;
import com.raihan.springsecurity.model.UsersInfoResponse;

public interface UsersService {
    UsersInfoResponse getAllUserInfo();

    String saveUserInfo(UserDto userDto);
}
