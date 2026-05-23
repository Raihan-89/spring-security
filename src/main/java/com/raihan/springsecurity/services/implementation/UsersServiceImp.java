package com.raihan.springsecurity.services.implementation;

import com.raihan.springsecurity.common.GenericResponse;
import com.raihan.springsecurity.entity.Users;
import com.raihan.springsecurity.model.UserDto;
import com.raihan.springsecurity.model.UsersInfoResponse;
import com.raihan.springsecurity.repository.UsersRepository;
import com.raihan.springsecurity.services.UsersInfoProjection;
import com.raihan.springsecurity.services.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsersServiceImp implements UsersService {
    private final UsersRepository usersRepository;

    @Override
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

    @Override
    public GenericResponse saveUserInfo(UserDto userDto) {

        Users existingUser = usersRepository.findUsersByUsernameOrEmailOrPhoneNumber(
                userDto.getUsername(),
                userDto.getEmail(),
                userDto.getPhoneNumber()
        );

        if (existingUser != null) {
            return new GenericResponse(HttpStatus.CONFLICT.toString(), "User already present with these information");
        }

        Users user = Users.builder()
                .fullName(userDto.getFullName())
                .username(userDto.getUsername())
                .phoneNumber(userDto.getPhoneNumber())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();


        usersRepository.save(user);
        log.info("User info saved successfully");
        return new GenericResponse(HttpStatus.CREATED.toString(), "User Created Successfully");
    }

    @Override
    public GenericResponse updateUser(UserDto userDto) {
        Users user = usersRepository.findUsersByEmail(userDto.getEmail());

        if  (user == null) {
            return new GenericResponse(HttpStatus.NOT_FOUND.toString(), "User not found with provided email");
        }

        user.setFullName(userDto.getFullName() != null ? userDto.getFullName() : user.getFullName());
        user.setPhoneNumber(userDto.getPhoneNumber() != null ? userDto.getPhoneNumber() : user.getPhoneNumber());
        user.setPassword(userDto.getPassword() != null ? userDto.getPassword() : user.getPassword());

        usersRepository.save(user);

        return new GenericResponse(HttpStatus.CREATED.toString(), "User info updated");
    }

    @Override
    public GenericResponse deleteUser(Integer id) {
        Users user = usersRepository.findById(id).orElse(null);

        if (user == null) {
            return new GenericResponse(HttpStatus.NOT_FOUND.toString(), "User "+ id +" is already not present in the system to delete");
        }

        usersRepository.deleteById(id);
        log.info("User {} successfully deleted from the system", id);

        return new GenericResponse(HttpStatus.OK.toString(), "User deleted successfully");
    }

    @PostAuthorize("returnObject.userList.get(0).username == authentication.name")
    @Override
    public UsersInfoResponse getParticularUserInfo(Integer id) {
        log.info("Fetching user information from database");

        Users user = usersRepository.findById(id).orElse(null);

        if (user == null) {
            return UsersInfoResponse.builder()
                    .userList(null)
                    .totalUser(0)
                    .build();
        }

        UserDto userDto = new UserDto(user);

        return UsersInfoResponse.builder()
                .userList(Collections.singletonList(userDto))
                .totalUser(1)
                .build();
    }
}
