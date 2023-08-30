package com.echobot.echobot.controllers;

import com.echobot.echobot.events.newmessage.VkEvent;
import com.echobot.echobot.requests.GetRequest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest
class VKControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GetRequest getRequest;
    @SpyBean
    VKController controller;
    @MockBean
    VkEvent vkEvent;
    @Value("${request.callbackApiConfirmation}")
    String callbackApiConfirmationTests;

    @Test
    void processVkEventTest_checkBehaviourForNewMessage() throws Exception {
        mockMvc.perform(post("")
                        .contentType("application/json")
                        .content("{\"type\":\"message_new\",\"object\":{\"message\":{\"from_id\":\"74598334\", \"text\":\"text\"}}}"))
                .andExpect(status().isOk());
        verify(getRequest).makeRequest(eq("messages.send"), isA(VkEvent.class), eq(null), eq(null), eq(null));
    }

    @Test
    void processVkEventTest_checkResponseForNewMessage()  {
        when(vkEvent.getType()).thenReturn("message_new");
        Assertions.assertEquals("ok", controller.processVkEvent(vkEvent));
    }

    @Test
    void processVkEventTest_checkBehaviourForUnknownEvent() throws Exception {
        mockMvc.perform(post("")
                        .contentType("application/json")
                        .content("{\"type\":\"unknown_event\"}"))
                .andExpect(status().isOk());
        verify(getRequest, never()).makeRequest(eq("messages.send"), isA(VkEvent.class), any(), any(), any());
    }

    @Test
    void processVkEventTest_checkResponseForUnknownEvent() throws Exception {
        when(vkEvent.getType()).thenReturn("unknown_event");
        Assertions.assertEquals("ok", controller.processVkEvent(vkEvent));
    }

    @Test
    void processVkEventTest_checkBehaviourForConfirmation() throws Exception {
        mockMvc.perform(post("")
                        .contentType("application/json")
                        .content("{\"type\":\"confirmation\",\"group_id\":219665708}"))
                .andExpect(status().isOk());
        verify(getRequest, never()).makeRequest(eq("messages.send"), isA(VkEvent.class), any(), any(), any());
    }

    @Test
    void processVkEventTest_checkResponseForConfirmation() {
        when(vkEvent.getType()).thenReturn("confirmation");
        Assertions.assertEquals(callbackApiConfirmationTests, controller.processVkEvent(vkEvent));
    }
}
