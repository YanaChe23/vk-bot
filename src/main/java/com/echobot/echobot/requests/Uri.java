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
    private String token;
    @Value("${request.apiVersion}")
    private String apiVersion;
    @Value("${request.host}")
    private String host;
    private String randomId;

    public MultiValueMap<String, String> addUriParams(String action, VkEvent event) {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        if (action.equals("messages.send")) {
            // made to solve the problem with randomness in unit tests
            if (getRandomId() == null) randomId = String.valueOf(System.currentTimeMillis());
            Message message = event.getObject().getMessage();
            requestParams.add("user_id", message.getFrom_id());
            requestParams.add("random_id", getRandomId());
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

    public String getToken() {
        return token;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getHost() {
        return host;
    }

    public String getRandomId() {
        return randomId;
    }
    public void setRandomId(String randomId) {
        this.randomId = randomId;
    }
}
