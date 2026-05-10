package com.raihan.springsecurity.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * @author Raihan-89
 */
@Component
public class JWTUtil {

    private static final String SECRET = "ThisIsTheSecretKeyToGenerateJWTToken";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET.getBytes());
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

    public String generateJWTToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
