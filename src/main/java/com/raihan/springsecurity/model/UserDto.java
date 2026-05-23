package com.raihan.springsecurity.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.raihan.springsecurity.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String fullName;
    private String username;
    private String phoneNumber;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public UserDto(Users user) {
        this.fullName = user.getFullName();
        this.username = user.getUsername();
        this.phoneNumber = user.getPhoneNumber();
        this.email = user.getEmail();
    }
}
