package com.echobot.echobot.requests;

import com.echobot.echobot.TestConfig;
import com.echobot.echobot.events.newMessage.Message;
import com.echobot.echobot.events.newMessage.VkEvent;
import com.echobot.echobot.events.newMessage.VkEventObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class,
        loader = AnnotationConfigContextLoader.class)
@TestPropertySource(locations = {"classpath:application.properties"})

class UriTest {
    @Autowired
    public Uri uri;
    public MultiValueMap<String, String> requestParams =  new LinkedMultiValueMap<>();
    private static final Message message = mock(Message.class);
    private static final VkEvent vkEvent = mock(VkEvent.class);
    private static final VkEventObject vkEventObject = mock(VkEventObject.class);
    @BeforeEach
    void setUp() {
        when(vkEvent.getObject()).thenReturn(vkEventObject);
        when(vkEventObject.getMessage()).thenReturn(message);
        when(message.getText()).thenReturn("Hello");
        when(message.getFrom_id()).thenReturn("1");
        requestParams.add("user_id", message.getFrom_id());
        requestParams.add("random_id", uri.getRandomId());
        requestParams.add("message", "You said: " + message.getText());
        requestParams.add("access_token", "test_token");
        requestParams.add("v", "5.131");
    }

    @Test
    void addUriParamsTest_ifReturnsCorrectParams() {
        Assertions.assertEquals(requestParams, uri.addUriParams("messages.send", vkEvent));
    }

    @Test
    void buildUriTest_ifReturnsCorrectUri() {
        String uriString = () "https://api.vk.com/method/messages.send?user_id=1&random_id=3&message=You said: Hello&access_token=test_token&v=5.131";
        Assertions.assertEquals(uriString, uri.buildUri("messages.send", vkEvent));
    }
}