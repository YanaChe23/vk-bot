package com.echobot.echobot.strategies;

import com.echobot.echobot.events.newmessage.VkEvent;
import com.echobot.echobot.requests.SendMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;


@Component
@RequiredArgsConstructor
public class NewMessageStrategy implements VkEventStrategy {
    private final SendMessageRequest sendMessageRequest;
    private HttpClient httpClient = HttpClient.newHttpClient();
    @Override
    public String handleEvent(VkEvent vkEvent)  {
        HttpResponse<String> r = sendMessageRequest.reply(vkEvent);
        return "ok";
    }
}
