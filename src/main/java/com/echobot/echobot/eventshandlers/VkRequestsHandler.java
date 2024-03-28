package com.echobot.echobot.eventshandlers;

import com.echobot.echobot.uribuilders.VkResponseUriBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.net.http.HttpClient;

abstract class VkRequestsHandler implements VkEventReplier {
    protected HttpClient httpClient = HttpClient.newHttpClient();
}