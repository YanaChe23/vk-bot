package com.echobot.echobot.eventshandlers;

import com.echobot.echobot.events.newmessage.VkEvent;
import com.echobot.echobot.exceptions.MakingRequestException;
import com.echobot.echobot.uribuilders.VkResponseUriBuilder;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Component
@Slf4j
public class MessagesSendHandler extends VkRequestsHandler {
    @Value("${request.requestReceivedConfirmation}")
    protected String requestReceivedConfirmation;

    protected final VkResponseUriBuilder baseUriBuilder;

    @Autowired
    public MessagesSendHandler(@Qualifier("messageSendUriBuilder") VkResponseUriBuilder baseUriBuilder) {
        this.baseUriBuilder = baseUriBuilder;
    }

    public String reply(VkEvent vkEvent) {
        HttpRequest request = buildRequest(vkEvent);
        HttpResponse <String> response = sendRequest(request);
        if (response.statusCode() != 200) log.error("New message response failed: " + response);
        return requestReceivedConfirmation;
    }

    private HttpResponse <String> sendRequest(HttpRequest request) {
        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
            throw new MakingRequestException("Failed to send a message.");
        }
    }

    public HttpRequest buildRequest(VkEvent vkEvent) {
        String uri = baseUriBuilder.buildUri(vkEvent);
        return HttpRequest.newBuilder(URI.create(uri))
                .GET()
                .build();
    }
}