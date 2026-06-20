package com.winwin.auth_api.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.winwin.auth_api.client.dto.TransformRequest;
import com.winwin.auth_api.client.dto.TransformResponse;

@Component
@RequiredArgsConstructor
public class DataApiClient {

    private final RestClient restClient;

    @Value("${data.api.url}")
    private String dataApiUrl;

    @Value("${internal.token}")
    private String internalToken;

    public TransformResponse transform(String text) {

        TransformRequest request =
                new TransformRequest(text);

        return restClient.post()
                .uri(dataApiUrl + "/api/transform")
                .header("X-Internal-Token", internalToken)
                .body(request)
                .retrieve()
                .body(TransformResponse.class);
    }  
}
