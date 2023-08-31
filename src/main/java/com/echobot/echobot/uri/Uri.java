package com.echobot.echobot.uri;

import com.echobot.echobot.events.newmessage.Message;
import com.echobot.echobot.events.newmessage.VkEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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
    public String buildUri(String action, VkEvent vkEvent) {
        MultiValueMap<String, String> uriParams = addUriParams(action, vkEvent);
        return UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(this.host)
                .queryParams(uriParams)
                .path("/method/" + action)
                .build()
                .toString();
    }

    public String generateRandomId() {
        return String.valueOf(System.currentTimeMillis());
    }

    public String generateTextMessage(VkEvent vkEvent) {
        String text;
        if (vkEvent != null
                && vkEvent.getObject() != null
                && vkEvent.getObject().getMessage() != null) {
            if (vkEvent.getObject().getMessage().getText().isEmpty()
                    || vkEvent.getObject().getMessage().getText() == null) {
                text =  "I don't work with attachments yet. Please send me a text message.";
            } else {
                text = "You said: " + vkEvent.getObject().getMessage().getText();
            }
        } else {
            text = "Something went wrong. Please try later.";
        }
        try {
            return URLEncoder.encode(text, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
