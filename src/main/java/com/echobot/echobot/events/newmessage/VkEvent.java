package com.echobot.echobot.events.newmessage;

import lombok.Getter;

@Getter
public class VkEvent {
    private String type;
    private VkEventObject object;
}
