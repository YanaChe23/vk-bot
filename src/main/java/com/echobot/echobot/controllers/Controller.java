package com.echobot.echobot.controllers;

import com.echobot.echobot.events.newMessage.Message;
import com.echobot.echobot.requests.ApiMethod;
import com.echobot.echobot.events.newMessage.VkEvent;
import com.echobot.echobot.requests.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
@PropertySource("classpath:application.properties")
public class Controller {
    @Autowired
    private Request request;
    @Value("${request.callbackApiConfirmation}")
    private String callbackApiConfirmation;

    @PostMapping()
    public String processVkEvent(@RequestBody VkEvent vkEvent) {
        if (vkEvent.type.equals("message_new")) {
            request.makeRequest(ApiMethod.POST, "messages.send", vkEvent);
        }
        // returning the string to ensure successful server confirmation during set up in VK
        // any specific response to events from VK is not required
        return callbackApiConfirmation;
    }

//    @PostMapping()
//    //public String testGet(@RequestBody String event) {
//    public String testGet(@RequestBody Message vkEvent) {
//
//        return "million million million vanilla cats";
//    }
}
