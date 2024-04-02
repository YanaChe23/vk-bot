package com.echobot.echobot.strategies;

import com.echobot.echobot.events.newmessage.VkEvent;
import com.echobot.echobot.eventshandlers.MessagesSendHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewMessageStrategy implements VkEventStrategy {
    private final MessagesSendHandler messagesSendHandler;

    @Override
    public String handleEvent(VkEvent vkEvent)  {
        return messagesSendHandler.reply(vkEvent);
    }
}