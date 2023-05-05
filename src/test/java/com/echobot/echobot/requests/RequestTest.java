package com.echobot.echobot.requests;

import com.echobot.echobot.events.newMessage.Message;
import com.echobot.echobot.events.newMessage.VkEvent;
import com.echobot.echobot.events.newMessage.VkEventObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;

import static org.mockito.Mockito.*;
@SpringBootTest
@ContextConfiguration(classes = RequestTest.class,
        loader = AnnotationConfigContextLoader.class)
@TestPropertySource(locations = {"classpath:application.properties"})
class RequestTest {
    @Mock
    private UriComponents uriComponents;
    @SpyBean
    private Request request;
    @MockBean
    private Uri uri;
    @MockBean
    private Message message;
    @MockBean
    private VkEvent vkEvent;
    @MockBean
    private VkEventObject vkEventObject;

    @BeforeEach
    void setUp() {
        when(uriComponents.toString()).thenReturn("https://api.vk.com/method/messages.send?user_id&random_id=3&message=You said: null&access_token=test_token&v=5.131");
        when(uri.buildUri("messages.send", vkEvent)).thenReturn(uriComponents);
        when(vkEvent.getObject()).thenReturn(vkEventObject);
        when(vkEventObject.getMessage()).thenReturn(message);
    }
    @Test
    void makeRequestTest_sendPostRequest() {
        request.makeRequest(ApiMethod.POST, "messages.send", vkEvent);
        verify(request).sendRequest(isA(UriComponents.class), isA(WebClient.RequestBodyUriSpec.class));
    }

    @Test
    void makeRequestTest_sendGetRequest() {
        request.makeRequest(ApiMethod.GET, "messages.send", vkEvent);
        verify(request).sendRequest(isA(UriComponents.class), isA(WebClient.RequestHeadersUriSpec.class));
    }
}