package com.echobot.echobot.requests;

import com.echobot.echobot.events.newmessage.Message;
import com.echobot.echobot.events.newmessage.VkEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class Uri {
    @Value("${request.token}")
    private String token;
    @Value("${request.apiVersion}")
    private String apiVersion;
    @Value("${request.host}")
    private String host;

    public MultiValueMap<String, String> addUriParams(String action, VkEvent event) {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("access_token", this.token);
        requestParams.add("v", this.apiVersion);
        if (action.equals("messages.send")) {
            // made this to solve the problem with randomness in unit tests
            Message message = event.getObject().getMessage();
            requestParams.add("user_id", message.getId());
            requestParams.add("random_id", generateRandomId());
            requestParams.add("message", "You said: " + message.getText());
        } else if(action.equals("users.get")) {
            requestParams.add("user_ids", event.getObject().getMessage().getId());
        }
        return requestParams;
    }
    public UriComponents buildUri(String action, VkEvent event) {
        MultiValueMap<String, String> uriParams = addUriParams(action, event);
        return UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(this.host)
                .queryParams(uriParams)
                .path("/method/" + action)
                .build();
    }

    public String getToken() {
        return token;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getHost() {
        return host;
    }

    public String generateRandomId() {
        return String.valueOf(System.currentTimeMillis());
    }
}
