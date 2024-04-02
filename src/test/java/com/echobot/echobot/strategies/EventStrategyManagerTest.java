package com.echobot.echobot.strategies;

import com.echobot.echobot.events.newmessage.VkEvent;
import com.echobot.echobot.events.newmessage.VkEventObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
class EventStrategyManagerTest {
    @Autowired
    private EventStrategyManager eventStrategyManager;
    @MockBean
    private ConfirmationStrategy confirmationStrategy;
    @MockBean
    private NewMessageStrategy newMessageStrategy;

    @Test
    public void handleEventMessageSendTest() {
        VkEvent vkEvent = new VkEvent("message_new", new VkEventObject());
        eventStrategyManager.handleEvent(vkEvent);
        verify(newMessageStrategy).handleEvent(vkEvent);
    }

    @Test
    public void handleEventConfirmationTest() {
        VkEvent vkEvent = new VkEvent("confirmation", new VkEventObject());
        eventStrategyManager.handleEvent(vkEvent);
        verify(confirmationStrategy).handleEvent(vkEvent);
    }

    @Test
    public void handleUnknownEventTest() {
        VkEvent vkEvent = new VkEvent("uunknown_event", new VkEventObject());
        eventStrategyManager.handleEvent(vkEvent);
        verify(newMessageStrategy, never()).handleEvent(vkEvent);
        verify(confirmationStrategy, never()).handleEvent(vkEvent);
    }
}