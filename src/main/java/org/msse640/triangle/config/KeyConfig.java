package org.msse640.triangle.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class KeyConfig {

    @Bean
    public SecretKey secretKey() {
        // Generate a secure key
        return Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }
}