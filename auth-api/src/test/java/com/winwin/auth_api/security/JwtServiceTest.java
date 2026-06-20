package com.winwin.auth_api.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private JwtService jwtService;

    @BeforeEach
    void setUp() {
        jwtService = new JwtService();

        // inject secret manually (since @Value not loaded in unit test)
        ReflectionTestUtils.setField(
                jwtService,
                "jwtSecret",
                "my-super-secret-key-my-super-secret-key"
        );

        jwtService.init();
    }

    @Test
    void shouldGenerateAndValidateToken() {
        String token = jwtService.generateToken("test@test.com");

        assertNotNull(token);
        assertTrue(jwtService.isValid(token));
        assertEquals("test@test.com", jwtService.extractEmail(token));
    }

    @Test
    void shouldReturnFalseForInvalidToken() {
        assertFalse(jwtService.isValid("invalid.token.here"));
    }
}
