package com.echobot.echobot.requests;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.http.HttpClient;

abstract class VkRequest implements VkEventReplier {
    @Value("${request.token}")
    String token;
    @Value("${request.apiVersion}")
    String apiVersion;
    @Value("${request.host}")
    String host;
    @Value("${request.scheme}")
    String scheme;
    HttpClient httpClient;
    final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
    @PostConstruct
    private void postConstruct() {
        params.add("access_token", this.token);
        params.add("v", this.apiVersion);
        httpClient = getHttpClient();
    }

    private HttpClient getHttpClient() {
        if (httpClient == null) httpClient = HttpClient.newHttpClient();
        return httpClient;
    }
}
