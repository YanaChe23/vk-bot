package com.echobot.echobot.events.newmessage;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class VkEvent {
    private String type;
    private VkEventObject object;
}
