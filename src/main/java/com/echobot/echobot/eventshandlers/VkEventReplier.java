package com.echobot.echobot.eventshandlers;

import com.echobot.echobot.events.newmessage.VkEvent;

import java.net.http.HttpResponse;

public interface VkEventReplier {
     String reply(VkEvent vkEvent);
}
