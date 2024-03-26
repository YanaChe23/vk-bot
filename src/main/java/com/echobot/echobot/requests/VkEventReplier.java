package com.echobot.echobot.requests;

import com.echobot.echobot.events.newmessage.VkEvent;

import java.net.http.HttpResponse;

public interface VkEventReplier {
     HttpResponse<String> reply(VkEvent vkEvent);
}
