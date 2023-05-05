package com.echobot.echobot.events.newMessage;

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
