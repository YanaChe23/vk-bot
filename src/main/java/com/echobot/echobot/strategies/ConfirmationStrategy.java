package com.echobot.echobot.strategies;

import com.echobot.echobot.events.newmessage.VkEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.http.HttpResponse;

@Component
@PropertySource("classpath:application.properties")

public class ConfirmationStrategy implements VkEventStrategy {
    @Value("${request.callbackApiConfirmation}")
    private String callbackApiConfirmation;
    @Override
    public String handleEvent(VkEvent vkEvent) {
        return callbackApiConfirmation;
    }
}
