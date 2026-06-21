package com.winwin.data_api.controller;

import com.winwin.data_api.service.TransformService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TransformController.class)
@TestPropertySource(properties = "internal.token=secret")
class TransformControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransformService transformService;

    @Test
    void shouldTransformText_whenTokenIsValid() throws Exception {

        when(transformService.transform("hello"))
                .thenReturn("HELLO");

        mockMvc.perform(post("/api/transform")
                        .header("X-Internal-Token", "secret")
                        .contentType("application/json")
                        .content("{\"text\":\"hello\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value("HELLO"));
    }

    @Test
    void shouldReturn403_whenTokenIsInvalid() throws Exception {

        mockMvc.perform(post("/api/transform")
                        .header("X-Internal-Token", "wrong")
                        .contentType("application/json")
                        .content("{\"text\":\"hello\"}"))
                .andExpect(status().isForbidden());
    }
}