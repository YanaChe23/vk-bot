package com.echobot.echobot.requests;

import com.echobot.echobot.events.newmessage.Message;
import com.echobot.echobot.events.newmessage.VkEvent;
import com.echobot.echobot.events.newmessage.VkEventObject;
import com.echobot.echobot.uri.Uri;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.net.http.HttpRequest;


import static org.mockito.Mockito.*;
@SpringBootTest
@ContextConfiguration(classes = GetRequestTest.class,
        loader = AnnotationConfigContextLoader.class)
class GetRequestTest {
    @SpyBean
    private GetRequest getRequest;
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
        when(uri.buildUri("messages.send", vkEvent)).thenReturn("https://api.vk.com/method/messages.send?user_id&random_id=3&message=You said: null&access_token=test_token&v=5.131");
        when(vkEvent.getObject()).thenReturn(vkEventObject);
        when(vkEventObject.getMessage()).thenReturn(message);
    }

    @Test
    void createBaseRequestTest_noAdditional_Settings() {
        HttpRequest request = getRequest.createRequest("https://api.vk.com/method/messages.send?user_id" +
                "&random_id=3&message=You_said:null&access_token=test_token&v=5.131", null, null, null);
        Assertions.assertEquals( "GET", request.method());
        Assertions.assertEquals("https://api.vk.com/method/messages.send?user_id&random_id=3" +
                "&message=You_said:null&access_token=test_token&v=5.131", request.uri().toString());
        Assertions.assertTrue(request.headers().map().isEmpty());
        Assertions.assertTrue(request.timeout().isEmpty());
        Assertions.assertTrue(request.version().isEmpty());
    }
}