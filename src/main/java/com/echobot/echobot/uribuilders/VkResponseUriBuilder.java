package com.echobot.echobot.uribuilders;

import com.echobot.echobot.events.newmessage.VkEvent;

public interface VkResponseUriBuilder {
    String buildUri(VkEvent vkEvent);
}
