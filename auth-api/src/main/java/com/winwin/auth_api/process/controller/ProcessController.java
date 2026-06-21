package com.winwin.auth_api.process.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.winwin.auth_api.process.service.ProcessService;
import com.winwin.auth_api.process.dto.ProcessResponse;
import com.winwin.auth_api.process.dto.ProcessRequest;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class ProcessController {

    private final ProcessService processService;

    @PostMapping("/process")
    public ProcessResponse process(
            @RequestBody ProcessRequest request,
            Authentication authentication
    ) {
        return processService.process(
            request.text(), 
            authentication.getName()
        );
    }
}