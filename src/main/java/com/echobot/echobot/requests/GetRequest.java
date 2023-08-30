package com.echobot.echobot.requests;

import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;

@Component
public class GetRequest extends Request {
    @Override
    public HttpRequest createRequest(String uriComponents, String[] headers, Duration timeout,
                                         HttpClient.Version version) {
        HttpRequest.Builder request = HttpRequest.newBuilder(URI.create(uriComponents))
                .GET();
        request = addSettingsToRequest(request, headers, timeout, version);
        return request.build();
    }

}
