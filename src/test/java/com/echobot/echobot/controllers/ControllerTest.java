package com.echobot.echobot.controllers;

import com.echobot.echobot.events.newMessage.VkEvent;
import com.echobot.echobot.requests.ApiMethod;
import com.echobot.echobot.requests.Request;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest
class WebControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private Request request;
    @Test
    void processVkEventTest_processNewMessage() throws Exception {
        mockMvc.perform(post("")
                        .contentType("application/json")
                        .content("{\"type\":\"message_new\",\"object\":{\"message\":{\"from_id\":\"74598334\", \"text\":\"text\"}}}"))
                .andExpect(status().isOk());
        verify(request).makeRequest(eq(ApiMethod.POST), eq("messages.send"), isA(VkEvent.class));
    }
}
