package com.echobot.echobot;

import com.echobot.echobot.events.newMessage.VkEvent;
import com.echobot.echobot.params.Uri;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;

import java.io.IOException;

@RestController
@RequestMapping()
@PropertySource("classpath:request.properties")
public class Controller {
    @Value("${request.callbackApiConfirmation}")
    private String callbackApiConfirmation;
    @PostMapping()
    public String getEvent(@RequestBody VkEvent event) throws IOException {
        if(event.type.equals("message_new")) {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                    SpringConfig.class
            );
            Uri uri = context.getBean("uri", Uri.class);
            UriComponents params = uri.buildUri("messages.send", event);
            Request request = context.getBean("request", Request.class);
            request.makeRequest(Method.POST, params);
        }
        //returning the string to ensure successful server confirmation during set up in VK
        return callbackApiConfirmation;
    }

    @GetMapping()
    public String test(){
        return "Test";
    }
}
