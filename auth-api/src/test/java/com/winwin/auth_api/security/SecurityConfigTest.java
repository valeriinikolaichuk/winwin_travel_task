package com.winwin.auth_api.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

class SecurityConfigTest {

    @Test
    void shouldCreatePasswordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        assertNotNull(encoder);

        String encoded = encoder.encode("test");

        assertTrue(encoder.matches("test", encoded));
    }
}
