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
            Message message = event.getObject().getMessage();
            requestParams.add("user_id", message.getFrom_id());
            requestParams.add("random_id", generateRandomId());
            requestParams.add("message", generateTextMessage(event));
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
    public String generateRandomId() {
        return String.valueOf(System.currentTimeMillis());
    }

    private String generateTextMessage(VkEvent vkEvent) {
        if (vkEvent != null
                && vkEvent.getObject() != null
                && vkEvent.getObject().getMessage() != null) {
            if (vkEvent.getObject().getMessage().getText().isEmpty()
                    || vkEvent.getObject().getMessage().getText() == null) {
                return "I don't work with attachments yet. Please send me a text message.";
            } else {
                return "You said: " + vkEvent.getObject().getMessage().getText();
            }
        }
        return "Something went wrong. Please try later.";
    }
}
