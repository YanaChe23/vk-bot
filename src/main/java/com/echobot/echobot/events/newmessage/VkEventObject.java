package com.echobot.echobot.events.newmessage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class VkEventObject {
    private Message message;

    public VkEventObject(Message message) {
        this.message = message;
    }
}
