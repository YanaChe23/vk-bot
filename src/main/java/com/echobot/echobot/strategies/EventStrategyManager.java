package com.echobot.echobot.strategies;

import com.echobot.echobot.events.newmessage.VkEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EventStrategyManager {
    private Map<String, VkEventStrategy> strategies;

    @Autowired
    public EventStrategyManager(NewMessageStrategy newMessageStrategy, ConfirmationStrategy confirmationStrategy) {
        this.strategies  = new HashMap<>();
        strategies.put("message_new", newMessageStrategy);
        strategies.put("confirmation", confirmationStrategy);
    }

    public String handleEvent(VkEvent vkEvent) {
        if (vkEvent.getType() != null && strategies.containsKey(vkEvent.getType())) {
            return strategies.get(vkEvent.getType()).handleEvent(vkEvent);
        }
        return "";
    }
}