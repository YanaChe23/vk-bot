package com.echobot.echobot.eventshandlers;

import com.echobot.echobot.events.newmessage.VkEvent;
import com.echobot.echobot.exceptions.SendingRequestException;
import com.echobot.echobot.uribuilders.VkResponseUriBuilder;
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

    protected final VkResponseUriBuilder uriBuilder;

    @Autowired
    public MessagesSendHandler(@Qualifier("messageSendUriBuilder") VkResponseUriBuilder messageSendUriBuilder) {
        this.uriBuilder = messageSendUriBuilder;
    }

    public String reply(VkEvent vkEvent) {
        HttpResponse <String> response = sendRequest(vkEvent);
        System.out.println(response);
        if (response.statusCode() != 200) log.error("New message response failed: " + response);
        return requestReceivedConfirmation;
    }

    private HttpResponse <String> sendRequest(VkEvent vkEvent) {
        HttpRequest request = buildRequest(vkEvent);
        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            log.error(e.getMessage());
            throw new SendingRequestException("Failed to send a message.");
        }
    }

    private HttpRequest buildRequest(VkEvent vkEvent) {
        String uri = uriBuilder.buildUri(vkEvent);
        return HttpRequest.newBuilder(URI.create(uri))
                .GET()
                .build();
    }
}