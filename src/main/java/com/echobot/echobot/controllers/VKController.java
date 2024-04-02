package com.echobot.echobot.controllers;

import com.echobot.echobot.events.newmessage.VkEvent;
import com.echobot.echobot.strategies.EventStrategyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequiredArgsConstructor
public class VKController {
    private final EventStrategyManager eventStrategyManager;

    @PostMapping("/")
    public String processVkEvent(@RequestBody VkEvent vkEvent) {
        return eventStrategyManager.handleEvent(vkEvent);
    }
}