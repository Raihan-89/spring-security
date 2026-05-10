package com.raihan.springsecurity.controller;

import com.raihan.springsecurity.model.AuthRequest;
import com.raihan.springsecurity.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Raihan-89
 */
@RestController
@RequestMapping("/api/jwt")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword())
            );
            return jwtUtil.generateJWTToken(request.getUsername());
            // return "jwt token generated";
        } catch (Exception e) {
            return "failed to generate jwt token";
        }
    }
}
