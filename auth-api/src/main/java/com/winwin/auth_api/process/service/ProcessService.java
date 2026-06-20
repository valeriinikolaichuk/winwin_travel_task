package com.winwin.auth_api.process.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.winwin.auth_api.user.repository.UserRepository;
import com.winwin.auth_api.log.repository.ProcessingLogRepository;
import com.winwin.auth_api.log.entity.ProcessingLog;
import com.winwin.auth_api.user.entity.User;
import com.winwin.auth_api.process.dto.ProcessResponse;
import com.winwin.auth_api.client.DataApiClient;
import com.winwin.auth_api.client.dto.TransformResponse;

@Service
@RequiredArgsConstructor
public class ProcessService {
    
    private final UserRepository userRepository;
    private final ProcessingLogRepository logRepository;
    private final DataApiClient dataApiClient;

    @Value("${internal.token}")
    private String internalToken;

    public ProcessResponse process(String text, String email) {

        // user lookup
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // call data-api
        TransformResponse response = dataApiClient.transform(text);
        String result = response.result();

        // save log
        ProcessingLog log = ProcessingLog.builder()
                .userId(user.getId())
                .inputText(text)
                .outputText(result)
                .createdAt(LocalDateTime.now())
                .build();

        logRepository.save(log);

        // return response
        return new ProcessResponse(result);
    }
}
