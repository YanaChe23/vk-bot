package com.echobot.echobot.events.newMessage;

import org.springframework.stereotype.Component;

public class VkEventObject {
    public Message message;

    public Message getMessage() {
        return message;
    }
}
