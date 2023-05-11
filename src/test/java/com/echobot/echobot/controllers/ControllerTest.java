package com.echobot.echobot.controllers;

import com.echobot.echobot.events.newmessage.VkEvent;
import com.echobot.echobot.requests.ApiMethod;
import com.echobot.echobot.requests.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest
class WebControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private Request request;
    @SpyBean
    Controller controller;
    @MockBean
    VkEvent vkEvent;
    @Value("${request.callbackApiConfirmation}")
    String callbackApiConfirmationTests;

    @Test
    void processVkEventTest_processNewMessage() throws Exception {
        mockMvc.perform(post("")
                        .contentType("application/json")
                        .content("{\"type\":\"message_new\",\"object\":{\"message\":{\"from_id\":\"74598334\", \"text\":\"text\"}}}"))
                .andExpect(status().isOk());
        verify(request).makeRequest(eq(ApiMethod.POST), eq("messages.send"), isA(VkEvent.class));
    }

    @Test
    void processVkEventTest_processUnknownEvent() throws Exception {
        mockMvc.perform(post("")
                        .contentType("application/json")
                        .content("{\"type\":\"unknown_event\"}"))
                .andExpect(status().isOk());
        verify(request, never()).makeRequest(eq(ApiMethod.POST), eq("messages.send"), isA(VkEvent.class));
    }

    @Test
    void processVkEventTest_checkWhatReturns() {
        Assertions.assertEquals(callbackApiConfirmationTests, controller.processVkEvent(vkEvent));
    }
}
