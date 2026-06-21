package com.winwin.auth_api.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JwtAuthenticationFilterTest {

    private final JwtService jwtService = mock(JwtService.class);

    private final JwtAuthenticationFilter filter =
            new JwtAuthenticationFilter(jwtService);

    @Test
    void shouldAuthenticateUser_whenValidTokenProvided() throws Exception {

        // given
        String token = "valid-token";
        String email = "user@test.com";

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain chain = mock(FilterChain.class);

        when(request.getHeader("Authorization"))
                .thenReturn("Bearer " + token);

        when(jwtService.isValid(token)).thenReturn(true);
        when(jwtService.extractEmail(token)).thenReturn(email);

        SecurityContextHolder.clearContext();

        // when
        filter.doFilterInternal(request, response, chain);

        // then
        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();

        assertNotNull(auth);
        assertEquals(email, auth.getName());

        verify(chain).doFilter(request, response);
    }
}
