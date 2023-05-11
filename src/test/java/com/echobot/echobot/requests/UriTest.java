package com.echobot.echobot.requests;

import com.echobot.echobot.events.newmessage.Message;
import com.echobot.echobot.events.newmessage.VkEvent;
import com.echobot.echobot.events.newmessage.VkEventObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = UriTest.class,
        loader = AnnotationConfigContextLoader.class)
@TestPropertySource(locations = {"classpath:application.properties"})

class UriTest {
    @SpyBean
    private Uri uri;
    @Mock
    private Message message;
    @Mock
    private VkEvent vkEvent;
    @Mock
    private VkEventObject vkEventObject;
    @Value("${request.token}")
    private String token;
    @Value("${request.apiVersion}")
    private String apiVersion;
    @Value("${request.host}")
    private String host;

    @BeforeEach
    void setUp() {
        when(vkEvent.getObject()).thenReturn(vkEventObject);
        when(vkEventObject.getMessage()).thenReturn(message);
        when(message.getFrom_id()).thenReturn("1");
        when(uri.generateRandomId()).thenReturn("3");
    }

    @Test
    void addUriParamsTest_checkUriParamIsCorrect() {
        when(message.getText()).thenReturn("Hello");
        Assertions.assertEquals("{access_token=[test_token], v=[5.131], user_id=[1], random_id=[3], " +
                "message=[You said: Hello]}", uri.addUriParams("messages.send", vkEvent).toString());
    }

    @Test
    void buildUriTest_ifReturnsCorrectUri() {
        when(message.getText()).thenReturn("Hello");
        Assertions.assertEquals("https://api.vk.com/method/messages.send?access_token=test_token&v=5.131" +
                "&user_id=1&random_id=3&message=You said: Hello", uri.buildUri("messages.send", vkEvent).toString());
    }

    @Test
    void generateTextMessage_checkMessageForAttachments() {
        when(message.getText()).thenReturn("");
        Assertions.assertEquals("I don't work with attachments yet. Please send me a text message.",
                uri.generateTextMessage(vkEvent));
    }

    @Test
    void generateTextMessage_checkMessageForText() {
        when(message.getText()).thenReturn("Hello");
        Assertions.assertEquals("You said: Hello", uri.generateTextMessage(vkEvent));
    }
}
