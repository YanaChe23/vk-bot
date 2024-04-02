package com.echobot.echobot.events.newmessage;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class VkEvent {
    private String type;
    private VkEventObject object;

    public VkEvent(String type, VkEventObject object) {
        this.type = type;
        this.object = object;
    }
}
