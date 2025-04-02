package org.msse640.triangle.controller;

import io.jsonwebtoken.Jwts;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.util.Date;

@RestController
public class TokenController {

    private final SecretKey secretKey;

    public TokenController(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    @GetMapping("/generate-token")
    public String generateToken() {
        // Generate a JWT token
        return Jwts.builder()
                .setSubject("user") // Set the subject (e.g., user identifier)
                .setIssuedAt(new Date()) // Set the issue date
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // Set expiration (1 hour)
                .signWith(secretKey) // Sign with the secure key
                .compact();
    }
}