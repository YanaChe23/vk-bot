package com.echobot.echobot.controllers;

import com.echobot.echobot.events.newmessage.VkEvent;
import com.echobot.echobot.strategies.EventStrategyManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class VKControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private EventStrategyManager eventStrategyManager;

    @Test
    public void processVkEventKnownStrategyTest() throws Exception {
        when(eventStrategyManager.handleEvent(any())).thenReturn("ok");
        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new VkEvent())))
                .andExpect(status().isOk())
                .andExpect(content().string("ok"));
    }

    @Test
    public void processVkEventUnKnownStrategyTest() throws Exception {
        when(eventStrategyManager.handleEvent(any())).thenReturn("");
        mockMvc.perform(post("/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new VkEvent())))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}