package com.echobot.echobot.events.newmessage;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Message {
    private String peer_id;
    private String text;
}