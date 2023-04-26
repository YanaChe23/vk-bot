package com.echobot.echobot;

import com.echobot.echobot.events.newMessage.Message;
import com.echobot.echobot.events.newMessage.VkEvent;
import com.echobot.echobot.events.newMessage.VkEventObject;
import com.echobot.echobot.requests.Request;
import com.echobot.echobot.requests.Uri;
import org.mockito.Mock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

@Configuration
public class TestConfig {
    @Bean
    public Uri uri() {
        return new Uri();
    }
    @Bean
    public Request request()  {return spy(Request.class);}

    @Bean
    public VkEventObject vkEventObject() {
        return mock(VkEventObject.class);
    }

    @Bean
    public Message message() {
        return mock(Message.class);
    }
    @Bean
    public VkEvent vkEvent() {
        return mock(VkEvent.class);
    }
}
