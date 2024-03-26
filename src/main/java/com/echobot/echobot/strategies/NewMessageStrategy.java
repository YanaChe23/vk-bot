package com.echobot.echobot.strategies;

import com.echobot.echobot.events.newmessage.VkEvent;
import com.echobot.echobot.requests.GetRequest;
import com.echobot.echobot.requests.SendMessageRequest;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Component
@RequiredArgsConstructor
public class NewMessageStrategy implements VkEventStrategy {
    private final GetRequest getRequest;
    private final SendMessageRequest sendMessageRequest;
    private HttpClient httpClient = HttpClient.newHttpClient();
    @Override
    public String handleEvent(VkEvent vkEvent)  {
        try {
            HttpResponse<String> r = sendMessageRequest.makeRequest(vkEvent);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "ok";
    }
}
