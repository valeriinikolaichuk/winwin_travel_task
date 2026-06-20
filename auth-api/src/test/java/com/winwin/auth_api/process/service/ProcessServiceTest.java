package com.winwin.auth_api.process.service;

import com.winwin.auth_api.client.DataApiClient;
import com.winwin.auth_api.client.dto.TransformResponse;
import com.winwin.auth_api.log.entity.ProcessingLog;
import com.winwin.auth_api.log.repository.ProcessingLogRepository;
import com.winwin.auth_api.process.dto.ProcessResponse;
import com.winwin.auth_api.user.entity.User;
import com.winwin.auth_api.user.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProcessServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProcessingLogRepository logRepository;

    @Mock
    private DataApiClient dataApiClient;

    @InjectMocks
    private ProcessService processService;

    @Test
    void shouldProcessTextAndSaveLog() {
        
        // given
        UUID userId = UUID.randomUUID();

        User user = User.builder()
                .id(userId)
                .email("test@test.com")
                .passwordHash("hash")
                .build();

        when(userRepository.findByEmail("test@test.com"))
                .thenReturn(Optional.of(user));

        when(dataApiClient.transform("hello"))
                .thenReturn(new TransformResponse("HELLO"));

        // when
        ProcessResponse response =
                processService.process("hello", "test@test.com");

        // then
        assertEquals("HELLO", response.result());

        ArgumentCaptor<ProcessingLog> captor =
                ArgumentCaptor.forClass(ProcessingLog.class);

        verify(logRepository).save(captor.capture());

        ProcessingLog savedLog = captor.getValue();

        assertEquals(userId, savedLog.getUserId());
        assertEquals("hello", savedLog.getInputText());
        assertEquals("HELLO", savedLog.getOutputText());
        assertNotNull(savedLog.getCreatedAt());

        verify(userRepository).findByEmail("test@test.com");
        verify(dataApiClient).transform("hello");
    }

    @Test
    void shouldThrowExceptionWhenUserNotFound() {
        // given
        when(userRepository.findByEmail("missing@test.com"))
                .thenReturn(Optional.empty());

        // when + then
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> processService.process("hello", "missing@test.com")
        );

        assertEquals("User not found", exception.getMessage());

        verify(dataApiClient, never()).transform(anyString());
        verify(logRepository, never()).save(any());
    }

    @Test
    void shouldNotSaveLogWhenDataApiFails() {
        // given
        User user = User.builder()
                .id(UUID.randomUUID())
                .email("test@test.com")
                .passwordHash("hash")
                .build();

        when(userRepository.findByEmail("test@test.com"))
                .thenReturn(Optional.of(user));

        when(dataApiClient.transform("hello"))
                .thenThrow(new RuntimeException("Data API unavailable"));

        // when + then
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> processService.process("hello", "test@test.com")
        );

        assertEquals("Data API unavailable", exception.getMessage());

        verify(logRepository, never()).save(any());
    }
}
