package com.echobot.echobot.requests;

import com.echobot.echobot.events.newMessage.Message;
import com.echobot.echobot.events.newMessage.VkEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class Uri {
    @Value("${request.token}")
    protected String token;
    @Value("${request.apiVersion}")
    protected String apiVersion;
    @Value("${request.host}")
    protected String host;
    public MultiValueMap<String, String> addUriParams(String action, VkEvent event) {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        if (action.equals("messages.send")) {
            Message message = event.object.message;
            requestParams.add("user_id", message.getFrom_id());
            requestParams.add("random_id", String.valueOf(System.currentTimeMillis()));
            requestParams.add("message", "You said: " + message.getText());
            requestParams.add("access_token", this.token);
            requestParams.add("v", this.apiVersion);
        }
        return requestParams;
    }
    public UriComponents buildUri(String action, VkEvent event) {
        MultiValueMap<String, String> uriParams = addUriParams(action, event);
        UriComponents uri2 =  UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(this.host)
                .queryParams(uriParams)
                .path("/method/" + action)
                .build();
        return uri2;
    }
}
