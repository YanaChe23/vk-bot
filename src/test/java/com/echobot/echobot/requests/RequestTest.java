package com.echobot.echobot.requests;

import com.echobot.echobot.events.newmessage.VkEvent;
import com.echobot.echobot.uri.Uri;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.time.Duration;
import static org.mockito.Mockito.*;
@SpringBootTest
@ContextConfiguration(classes = RequestTest.class,
        loader = AnnotationConfigContextLoader.class)
class RequestTest {
    @SpyBean
    private GetRequest request;
    @MockBean
    private Uri uri;
    @MockBean
    private VkEvent vkEvent;
    String[] headers = {"Content-Type", "application/json", "charset", "UTF-8"};
    Duration timeout = Duration.ofSeconds(3);
    HttpClient.Version version = HttpClient.Version.HTTP_2;
    HttpRequest.Builder rawRequest = HttpRequest.newBuilder(URI.create("https://api.vk.com/method/messages.send"))
            .GET();
    @Test
    void addSettingsToRequestTest_withAllSettings() {
        HttpRequest requestWithSettings = request.addSettingsToRequest(rawRequest, headers, timeout, version).build();
        Assertions.assertEquals("{charset=[UTF-8], Content-Type=[application/json]}", requestWithSettings.headers().map().toString());
        Assertions.assertEquals(3, requestWithSettings.timeout().get().getSeconds());
        Assertions.assertEquals(HttpClient.Version.HTTP_2, requestWithSettings.version().get());
    }
    @Test
    void addSettingsToRequestTest_withoutSettings() {
        HttpRequest requestWithSettings = request.addSettingsToRequest(rawRequest, null, null,
                null).build();
        Assertions.assertTrue(requestWithSettings.headers().map().isEmpty());
        Assertions.assertTrue(requestWithSettings.timeout().isEmpty());
        Assertions.assertTrue(requestWithSettings.version().isEmpty());
    }
    @Test
    void addSettingsToRequestTest_oneSetting() {
        HttpRequest requestWithSettings = request.addSettingsToRequest(rawRequest, null, null,
                version).build();
        Assertions.assertTrue(requestWithSettings.headers().map().isEmpty());
        Assertions.assertTrue(requestWithSettings.timeout().isEmpty());
        Assertions.assertEquals(HttpClient.Version.HTTP_2, requestWithSettings.version().get());
    }
    @Test
    void makeRequestTest() {
        when(uri.buildUri("action", vkEvent)).thenReturn("https://api.vk.com/method/messages.send");
        request.makeRequest("action", vkEvent, null, null, null);
        verify(uri, times(1)).buildUri("action", vkEvent);
        verify(request, times(1)).createRequest("https://api.vk.com/method/messages.send",
                null, null,  null);
        verify(request, times(1)).sendRequest(isA(HttpRequest.class));
    }
}