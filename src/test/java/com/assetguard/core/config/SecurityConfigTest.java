package com.assetguard.core.config;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SecurityConfigTest {

    @Test
    void passwordEncoderShouldEncodePassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "testPassword";
        String encodedPassword = encoder.encode(rawPassword);

        assertNotNull(encodedPassword);
        assertTrue(encoder.matches(rawPassword, encodedPassword));
    }

    @Test
    void passwordEncoderShouldNotMatchWrongPassword() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPassword = encoder.encode("correctPassword");

        assertTrue(encoder.matches("correctPassword", encodedPassword));
        assertTrue(!encoder.matches("wrongPassword", encodedPassword));
    }
}
