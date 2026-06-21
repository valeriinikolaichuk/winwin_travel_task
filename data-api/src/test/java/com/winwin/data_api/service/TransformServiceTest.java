package com.winwin.data_api.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransformServiceTest {

    private final TransformService transformService = new TransformService();

    @Test
    void shouldConvertTextToUpperCase() {

        String result = transformService.transform("hello");

        assertEquals("HELLO", result);
    }

    @Test
    void shouldHandleEmptyString() {

        String result = transformService.transform("");

        assertEquals("", result);
    }
}
