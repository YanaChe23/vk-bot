package com.echobot.echobot.events.newMessage;

import org.springframework.stereotype.Component;

public class Message {
    public String from_id;
    public String text;
    public String getFrom_id() {
        return from_id;
    }
    public String getText() {
        return text;
    }
}
