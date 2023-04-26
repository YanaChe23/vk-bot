package com.echobot.echobot.requests;

import com.echobot.echobot.TestConfig;
import com.echobot.echobot.events.newMessage.Message;
import com.echobot.echobot.events.newMessage.VkEvent;
import com.echobot.echobot.events.newMessage.VkEventObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;

import static org.mockito.Mockito.*;

@SpringBootTest
@ContextConfiguration(classes = TestConfig.class,
        loader = AnnotationConfigContextLoader.class)
@TestPropertySource(locations = {"classpath:application.properties"})
// попробовать с экземпляром
class RequestTest {
    private WebClient webclient;
    private UriComponents uriComponents = mock(UriComponents.class);
    @Autowired
    private Uri uri;
    @Autowired
    private Request request;
    @Autowired
    private Message message;
    @Autowired
    private VkEvent vkEvent;
    @Autowired
    private VkEventObject vkEventObject;


    @BeforeEach
    void setUp() {
        webclient = WebClient.create();
        UriComponents uriComponents = mock(UriComponents.class);
        when(vkEvent.getObject()).thenReturn(vkEventObject);
        when(vkEventObject.getMessage()).thenReturn(message);
    }

    @Test
    void makeRequestTest() {
        WebClient.RequestBodyUriSpec client = webclient.post();
        System.out.println("Тест ури " + request);
        request.makeRequest(ApiMethod.POST, "messages.send", vkEvent);

    }
    @Test
    void sendPostRequest() {
    }
}