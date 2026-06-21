package com.winwin.data_api.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import com.winwin.data_api.service.*;
import com.winwin.data_api.dto.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TransformController {

    private final TransformService transformService;

    @Value("${internal.token}")
    private String internalToken;

    @PostMapping("/transform")
    public TransformResponse transform(
            @RequestHeader("X-Internal-Token") String token,
            @RequestBody TransformRequest request) {

        if (!internalToken.equals(token)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }

        String result = transformService.transform(request.text());

        return new TransformResponse(result);
    }
}
