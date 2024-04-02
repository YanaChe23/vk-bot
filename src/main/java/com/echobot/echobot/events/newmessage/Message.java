package com.echobot.echobot.events.newmessage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class Message {
    private String peer_id;
    private String text;

    public Message(String peer_id, String text) {
        this.peer_id = peer_id;
        this.text = text;
    }
}