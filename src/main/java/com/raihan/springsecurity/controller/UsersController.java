package com.raihan.springsecurity.controller;

import com.raihan.springsecurity.model.UserDto;
import com.raihan.springsecurity.services.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UsersController {
    private final UsersService usersService;

    @GetMapping("/info")
    public ResponseEntity<?> getAllUsersInfo() {
        log.info("Received request to get all users information");

        return ResponseEntity.ok(usersService.getAllUserInfo());
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUserInfo(@RequestBody UserDto userDto) {
        log.info("Received request to save user information");

        return ResponseEntity.ok(usersService.saveUserInfo(userDto));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUserInfo(@RequestBody UserDto userDto) {
        log.info("Received request to update user {} info", userDto.getUsername());

        return ResponseEntity.ok(usersService.updateUser(userDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam("id") Integer id) {
        log.info("Received Request to delete user with id: {}", id);

        return ResponseEntity.ok(usersService.deleteUser(id));
    }
}
