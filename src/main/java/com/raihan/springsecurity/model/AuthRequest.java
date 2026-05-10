package com.raihan.springsecurity.model;

import lombok.Data;

/**
 * @author Raihan-89
 */

@Data
public class AuthRequest {
    private String username;
    private String password;
}
