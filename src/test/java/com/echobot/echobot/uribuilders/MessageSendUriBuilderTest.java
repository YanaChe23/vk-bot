package com.echobot.echobot.uribuilders;

import com.echobot.echobot.events.newmessage.Message;
import com.echobot.echobot.events.newmessage.VkEvent;
import com.echobot.echobot.events.newmessage.VkEventObject;
import com.echobot.echobot.generators.RandomIdGenerator;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class MessageSendUriBuilderTest {
    @Autowired
    private BaseVkResponseUriBuilder messageSendUriBuilder;
    @MockBean
    private RandomIdGenerator randomIdGenerator;
    private VkEvent vkEvent;
    @PostConstruct
    public void setUp() {
        Message message = new Message("1", "Text");
        VkEventObject vkEventObject = new VkEventObject(message);
        vkEvent = new VkEvent("message_new", vkEventObject);

        when(randomIdGenerator.generate()).thenReturn("5210645176079148914");
    }

    @Test
    public void buildUriTest() {
        assertEquals("https://api.vk.com/method/messages.send?access_token=test_token&v=5.81&peer_id=1" +
                "&random_id=5210645176079148914&message=You+said%3A+Text", messageSendUriBuilder.buildUri(vkEvent));
    }

    @Test
    public void buildUriMessageFromPublicTest() {
        Message messageFromPublic = new Message("1", "[club219665708|Felix Felicis] hello");
        VkEventObject vkEventObjectFromPublic = new VkEventObject(messageFromPublic);
        VkEvent vkEventFromPublic = new VkEvent("message_new", vkEventObjectFromPublic);

        assertEquals("https://api.vk.com/method/messages.send?access_token=test_token&v=5.81&peer_id=1" +
                "&random_id=5210645176079148914&message=You+said%3A+hello", messageSendUriBuilder.buildUri(vkEventFromPublic));
    }
}