// TODO почему пост от vk приходит два раза?
package com.echobot.echobot.controllers;

import com.echobot.echobot.requests.ApiMethod;
import com.echobot.echobot.events.newMessage.VkEvent;
import com.echobot.echobot.requests.Request;
import com.echobot.echobot.requests.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;

@RestController
@RequestMapping()
@PropertySource("classpath:application.properties")
public class Controller {
    @Autowired
    private Request request;
    @Autowired
    private Uri uri;
    @Value("${request.callbackApiConfirmation}")
    private String callbackApiConfirmation;

    @PostMapping()
    public String getEvent(@RequestBody VkEvent event) {
        if (event.type.equals("message_new")) {
            UriComponents params = uri.buildUri("messages.send", event);
            request.makeRequest(ApiMethod.POST, params);
        }
        // returning the string to ensure successful server confirmation during set up in VK
        // any specific response to events from VK is not required
        return callbackApiConfirmation;
    }
}
