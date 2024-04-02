package com.echobot.echobot.eventshandlers;

import com.echobot.echobot.events.newmessage.Message;
import com.echobot.echobot.events.newmessage.VkEvent;
import com.echobot.echobot.events.newmessage.VkEventObject;
import com.echobot.echobot.exceptions.SendingRequestException;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest
class MessagesSendHandlerTest {
    @Autowired
    private MessagesSendHandler messagesSendHandler;
    @Value("${request.requestReceivedConfirmation}")
    protected String requestReceivedConfirmation;
    @MockBean
    private HttpClient httpClient;
    @Mock
    private HttpResponse<String> response;
    private VkEvent vkEvent;

    @PostConstruct
    public void setUp() {
        Message message = new Message("1", "Text");
        VkEventObject vkEventObject = new VkEventObject(message);
        vkEvent = new VkEvent("message_new", vkEventObject);
    }

    @Test
    public void replySuccessTest() throws IOException, InterruptedException {
        when(httpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString()))).thenReturn(response);
        assertEquals(requestReceivedConfirmation, messagesSendHandler.reply(vkEvent));
    }

    @Test
    public void replyIOExceptionTest() throws IOException, InterruptedException {
        when(httpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
                .thenThrow(new IOException());
        assertThrows(SendingRequestException.class, () -> messagesSendHandler.reply(vkEvent));
    }

    @Test
    public void replyInterruptedExceptionTest() throws IOException, InterruptedException {
        when(httpClient.send(any(HttpRequest.class), eq(HttpResponse.BodyHandlers.ofString())))
                .thenThrow(new InterruptedException());
        assertThrows(SendingRequestException.class, () -> messagesSendHandler.reply(vkEvent));
    }
}