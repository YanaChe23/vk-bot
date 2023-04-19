package com.echobot.echobot.events.newMessage;

import org.springframework.stereotype.Component;



public class VkEvent {
    public String type;
    public VkEventObject object;

    public String getType() {
        return type;
    }

    public VkEventObject getObject() {
        return object;
    }
}
