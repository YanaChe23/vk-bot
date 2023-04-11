package com.echobot.echobot;

import com.echobot.echobot.events.newMessage.VkEvent;
import com.echobot.echobot.params.Params;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;

import java.io.IOException;

@RestController
@RequestMapping()
@PropertySource("classpath:request.properties")
public class Controller {

    @PostMapping()
    public String getEvent(@RequestBody VkEvent event) throws IOException {
        if(event.type.equals("message_new")) {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                    SpringConfig.class
            );
            Params p = context.getBean("params", Params.class);
            Request req = context.getBean("request", Request.class);

            UriComponents params = p.buildUri("messages.send", event);
            req.makeRequest(Method.POST, params);
        }
        // TODO request to mark as read
        //returning the string to ensure successful server confirmation during set up in VK
        return "8883909f";
    }

    @GetMapping()
    public String test(){
        return "Test";
    }
}
