package com.echobot.echobot;

import com.echobot.echobot.events.VkEvent;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class Controller {
    @PostMapping()
    public String getEvent(@RequestBody VkEvent event) {
        if(event.type.equals("message_new")) {
            System.out.println("Hello");

        }
        // TODO request to mark as read
        //returning the string to ensure successful server confirmation during set up in VK
        return "aeb0e9aa";
    }

    @GetMapping()
    public String test(){
        return "Test";
    }
}
