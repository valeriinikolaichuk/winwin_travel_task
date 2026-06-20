package com.winwin.auth_api.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class SecurityConfigTest {

    @Test
    void shouldCreatePasswordEncoder() {
        SecurityConfig config = new SecurityConfig();

        PasswordEncoder encoder = config.passwordEncoder();

        assertNotNull(encoder);

        String encoded = encoder.encode("test");

        assertTrue(encoder.matches("test", encoded));
    }
}
