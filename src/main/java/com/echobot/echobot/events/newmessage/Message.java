package com.echobot.echobot.events.newmessage;

import lombok.Getter;

@Getter
public class Message {
    private String peer_id;
    private String text;
}