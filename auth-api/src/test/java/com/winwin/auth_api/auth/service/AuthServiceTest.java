package com.winwin.auth_api.auth.service;

import com.winwin.auth_api.auth.dto.LoginRequest;
import com.winwin.auth_api.auth.dto.LoginResponse;
import com.winwin.auth_api.auth.dto.RegisterRequest;
import com.winwin.auth_api.security.JwtService;
import com.winwin.auth_api.user.entity.User;
import com.winwin.auth_api.user.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthService authService;

    @Test
    void shouldRegisterUser() {
        RegisterRequest request = new RegisterRequest("test@test.com", "123");

        when(userRepository.existsByEmail("test@test.com")).thenReturn(false);
        when(passwordEncoder.encode("123")).thenReturn("hashed");

        authService.register(request);

        verify(userRepository).save(any(User.class));
        verify(passwordEncoder).encode("123");
    }

    @Test
    void shouldThrowExceptionWhenEmailExists() {
        RegisterRequest request = new RegisterRequest("test@test.com", "123");

        when(userRepository.existsByEmail("test@test.com")).thenReturn(true);

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> authService.register(request)
        );

        assertEquals("Email already exists", ex.getMessage());

        verify(userRepository, never()).save(any());
    }

    @Test
    void shouldLoginSuccessfully() {
        LoginRequest request = new LoginRequest("test@test.com", "123");

        User user = User.builder()
                .email("test@test.com")
                .passwordHash("hashed")
                .build();

        when(userRepository.findByEmail("test@test.com"))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.matches("123", "hashed")).thenReturn(true);
        when(jwtService.generateToken("test@test.com")).thenReturn("token123");

        LoginResponse response = authService.login(request);

        assertEquals("token123", response.token());
    }

    @Test
    void shouldFailLoginWhenUserNotFound() {
        LoginRequest request = new LoginRequest("test@test.com", "123");

        when(userRepository.findByEmail("test@test.com"))
                .thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> authService.login(request)
        );

        assertEquals("Invalid credentials", ex.getMessage());

        verify(jwtService, never()).generateToken(any());
    }

    @Test
    void shouldFailLoginWhenPasswordIncorrect() {
        LoginRequest request = new LoginRequest("test@test.com", "wrong");

        User user = User.builder()
                .email("test@test.com")
                .passwordHash("hashed")
                .build();

        when(userRepository.findByEmail("test@test.com"))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.matches("wrong", "hashed")).thenReturn(false);

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> authService.login(request)
        );

        assertEquals("Invalid credentials", ex.getMessage());

        verify(jwtService, never()).generateToken(any());
    }
}
