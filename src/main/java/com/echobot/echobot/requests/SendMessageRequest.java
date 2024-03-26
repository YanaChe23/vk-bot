package com.echobot.echobot.requests;

import com.echobot.echobot.events.newmessage.Message;
import com.echobot.echobot.events.newmessage.VkEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import com.echobot.echobot.uri.Uri;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
public class SendMessageRequest {
    private HttpClient httpClient;
    private Uri uri;

    @Value("${request.token}")
    private String token;
    @Value("${request.apiVersion}")
    private String apiVersion;
    @Value("${request.host}")
    private String host;

    private HttpClient getHttpClient() {
        if (httpClient == null) httpClient = HttpClient.newHttpClient();
        return httpClient;
    }

    public HttpRequest createRequest(VkEvent vkEvent) {
        String uriComponents = uri.buildUri("messages.send", vkEvent);
        return HttpRequest.newBuilder(URI.create(uriComponents))
                .GET().build();
    }


    public HttpResponse <String> makeRequest(VkEvent vkEvent) throws IOException, InterruptedException {
        httpClient = getHttpClient();
        HttpRequest request = createRequest(vkEvent);
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}


// что нужно для запроса:
// 1. клиент
// 2. урл: параметры и статичная шляпа
// 3. реквест: урл + обвесы: хедеры, таймауты etc