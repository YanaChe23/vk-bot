package com.echobot.echobot.strategies;

import com.echobot.echobot.events.newmessage.VkEvent;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
class ConfirmationStrategyTest {
    @Autowired
    private ConfirmationStrategy confirmationStrategy;
    @Value("${request.callbackApiConfirmation}")
    private String callbackApiConfirmation;

    @Test
    void handleEvent() {
        assertEquals(callbackApiConfirmation, confirmationStrategy.handleEvent(new VkEvent()));
    }
}