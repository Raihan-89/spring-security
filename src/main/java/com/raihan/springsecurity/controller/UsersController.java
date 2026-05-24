package com.raihan.springsecurity.controller;

import com.raihan.springsecurity.model.UserDto;
import com.raihan.springsecurity.services.UsersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UsersController {
    private final UsersService usersService;

    @GetMapping("/info")
//    @PreAuthorize("hasAuthority('READ')")
//    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> getAllUsersInfo() {
        log.info("Received request to get all users information");

        return ResponseEntity.ok(usersService.getAllUserInfo());
    }

    @GetMapping("/get-only")
    public ResponseEntity<?> getUserInfoOfTheSameUser(@RequestParam("id") Integer id) {
        log.info("Received request to get particular user information");

        return ResponseEntity.ok(usersService.getParticularUserInfo(id));
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('WRITE')")
    public ResponseEntity<?> saveUserInfo(@RequestBody UserDto userDto) {
        log.info("Received request to save user information");

        return ResponseEntity.ok(usersService.saveUserInfo(userDto));
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('WRITE')")
    public ResponseEntity<?> updateUserInfo(@RequestBody UserDto userDto) {
        log.info("Received request to update user {} info", userDto.getUsername());

        return ResponseEntity.ok(usersService.updateUser(userDto));
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<?> deleteUser(@RequestParam("id") Integer id) {
        log.info("Received Request to delete user with id: {}", id);

        return ResponseEntity.ok(usersService.deleteUser(id));
    }
}
