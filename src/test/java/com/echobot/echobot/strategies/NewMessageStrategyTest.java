package com.echobot.echobot.strategies;

import com.echobot.echobot.events.newmessage.Message;
import com.echobot.echobot.events.newmessage.VkEvent;
import com.echobot.echobot.events.newmessage.VkEventObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NewMessageStrategyTest {
    @Autowired
    private NewMessageStrategy newMessageStrategy;
    @Value("${request.requestReceivedConfirmation}")
    protected String requestReceivedConfirmation;
    @Test
    void handleEvent() {
        Message message = new Message("1", "Text");
        VkEventObject vkEventObject = new VkEventObject(message);
        VkEvent vkEvent = new VkEvent("message_new", vkEventObject);
        assertEquals(requestReceivedConfirmation, newMessageStrategy.handleEvent(vkEvent));
    }
}