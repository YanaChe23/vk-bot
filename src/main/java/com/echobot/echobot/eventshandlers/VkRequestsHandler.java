package com.echobot.echobot.eventshandlers;

import org.springframework.beans.factory.annotation.Autowired;
import java.net.http.HttpClient;

abstract class VkRequestsHandler implements VkEventReplier {
    @Autowired
    protected HttpClient httpClient;
}