package com.raihan.springsecurity.services.implementation;

import com.raihan.springsecurity.entity.Users;
import com.raihan.springsecurity.model.UserDto;
import com.raihan.springsecurity.model.UsersInfoResponse;
import com.raihan.springsecurity.repository.UsersRepository;
import com.raihan.springsecurity.services.UsersInfoProjection;
import com.raihan.springsecurity.services.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsersServiceImp implements UsersService {
    private final UsersRepository usersRepository;

    public UsersInfoResponse getAllUserInfo() {
        log.info("Fetching users information from database");

        List<UsersInfoProjection> userListProjection = usersRepository.getAllUser();

        List<UserDto> userList = userListProjection.stream()
                .map(user -> {
                    UserDto userDto = new UserDto();
                    userDto.setFullName(user.getFullName());
                    userDto.setUsername(user.getUsername());
                    userDto.setPhoneNumber(user.getPhoneNumber());
                    userDto.setEmail(user.getEmail());

                    return userDto;
                })
                .toList();

        log.info("All user response send");
        return UsersInfoResponse.builder()
                .userList(userList.stream().toList())
                .totalUser(userList.size())
                .build();
    }

    public String saveUserInfo(UserDto userDto) {

        Users user = Users.builder()
                .fullName(userDto.getFullName())
                .username(userDto.getUsername())
                .phoneNumber(userDto.getPhoneNumber())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();


        usersRepository.save(user);
        log.info("User info saved successfully");
        return "User info saved successfully";
    }
}
