package com.echobot.echobot.controllers;

import com.echobot.echobot.events.newmessage.VkEvent;
import com.echobot.echobot.requests.GetRequest;
import com.echobot.echobot.uri.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;

@RestController
@PropertySource("classpath:application.properties")
public class VKController {
    @Autowired
    private GetRequest getRequest;
    @Value("${request.callbackApiConfirmation}")
    private String callbackApiConfirmation;
    @PostMapping("/")
    public String processVkEvent(@RequestBody VkEvent vkEvent) {
        if (vkEvent.getType() != null) {
            if (vkEvent.getType().equals("confirmation")) {
                return callbackApiConfirmation;
            } else if (vkEvent.getType().equals("message_new")) {
                getRequest.makeRequest("messages.send", vkEvent, null, null, null);
            }
        }
        // VK expects to receive "ok" on every request except confirmation
        return "ok";
    }
}

