package org.msse640.triangle.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import javax.crypto.SecretKey;

@Configuration
public class JwtConfig {

    private final SecretKey secretKey;

    public JwtConfig(SecretKey secretKey) {
        this.secretKey = secretKey;
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        // Configure the NimbusJwtDecoder with the shared secret key
        return NimbusJwtDecoder.withSecretKey(secretKey).build();
    }
}