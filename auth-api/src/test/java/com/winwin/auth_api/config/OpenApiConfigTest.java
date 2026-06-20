package com.winwin.auth_api.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OpenApiConfigTest {

    @Test
    void contextLoads(ApplicationContext context) {
        assertNotNull(context.getBean(OpenApiConfig.class));
    }
}
