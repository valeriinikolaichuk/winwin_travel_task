package com.winwin.auth_api.auth.dto;

public record RegisterRequest(
        String email,
        String password
) {}
