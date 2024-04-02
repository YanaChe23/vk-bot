package com.echobot.echobot.strategies;

import com.echobot.echobot.events.newmessage.VkEvent;
import org.springframework.http.ResponseEntity;

import java.net.http.HttpResponse;

public interface VkEventStrategy {
    String handleEvent(VkEvent vkEvent);
}
