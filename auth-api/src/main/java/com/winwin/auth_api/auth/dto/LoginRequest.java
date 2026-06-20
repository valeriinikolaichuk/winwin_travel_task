package com.winwin.auth_api.auth.dto;

public record LoginRequest(
        String email,
        String password
) {}