package com.echobot.echobot.requests;

import com.echobot.echobot.events.newmessage.Message;
import com.echobot.echobot.events.newmessage.VkEvent;
import com.echobot.echobot.exceptions.EventParsingException;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import java.util.Optional;

@Component
public class SendMessageRequest extends VkRequest {

    public HttpResponse <String> reply(VkEvent vkEvent) {
        HttpRequest request = buildRequest(vkEvent);
        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public HttpRequest buildRequest(VkEvent vkEvent) {
        return HttpRequest.newBuilder(
                    URI.create(buildUri(vkEvent))
                )
                .GET()
                .build();
    }

    private String buildUri(VkEvent vkEvent) {
        return UriComponentsBuilder.newInstance()
                .scheme(scheme)
                .host(this.host)
                .queryParams(getParams(vkEvent))
                .path("/method/messages.send")
                .build()
                .toString();
    }

    private MultiValueMap<String, String> getParams(VkEvent vkEvent) {
        Message message = Optional.ofNullable(vkEvent.getObject().getMessage())
                .orElseThrow(() -> new EventParsingException("Failed to get a message."));

        params.add("peer_id", message.getPeer_id());
        params.add("random_id", generateRandomId());
        params.add("message", getText(message));
        return params;
    }

    String generateRandomId() {
        return String.valueOf(System.currentTimeMillis());
    }
    String getText(Message message)  {
        String textToEcho = Optional.ofNullable(message.getText())
                .orElseThrow(() -> new EventParsingException("Failed to get a message text."));
        String textToSend = "If you send me a written text I echo it.";
        if (!textToEcho.isEmpty())  textToSend = "You said: " + textToEcho;
        return URLEncoder.encode(textToSend, StandardCharsets.UTF_8);
    }
}