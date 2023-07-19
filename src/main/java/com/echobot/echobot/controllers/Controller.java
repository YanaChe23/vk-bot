package com.echobot.echobot.controllers;

import com.echobot.echobot.requests.ApiMethod;
import com.echobot.echobot.events.newmessage.VkEvent;
import com.echobot.echobot.requests.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

@RestController
@PropertySource("classpath:application.properties")
public class Controller {
    @Autowired
    private Request request;
    @Value("${request.callbackApiConfirmation}")
    private String callbackApiConfirmation;

    @PostMapping("/")
    public String processVkEvent(@RequestBody VkEvent vkEvent) {
        if (vkEvent.getType() != null) {
            if (vkEvent.getType().equals("confirmation")) {
                return callbackApiConfirmation;
            } else if (vkEvent.getType().equals("message_new")) {
                request.makeRequest(ApiMethod.POST, "messages.send", vkEvent);
            }
        }
        // VK expects to receive "ok" on every request except confirmation
        return "ok";
    }
}

