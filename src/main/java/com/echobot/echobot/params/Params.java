package com.echobot.echobot.params;


import com.echobot.echobot.events.newMessage.Message;
import com.echobot.echobot.events.newMessage.VkEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Component
public class Params {
      private final MultiValueMap<String, String> requestParams  = new LinkedMultiValueMap<>();
      @Value("${request.token}")
      public String token;
      @Value("${request.apiVersion}")
      public String apiVersion;
      @Value("${request.host}")
      public String host;
    FileReader reader = null;
    Properties prop = new Properties();


    public Params() throws IOException {
        reader = new FileReader("/Users/chuprunovayana/Downloads/vk_bot/src/main/resources/request.properties");
        prop.load(reader);

    }

    public MultiValueMap<String, String> combineParams(String action, VkEvent event) {
        if (action.equals("messages.send")) {
            Message message = event.object.message;
            requestParams.add("user_id", message.getFrom_id());
            requestParams.add("random_id", String.valueOf(System.currentTimeMillis()));
            requestParams.add("message", message.getText());
            requestParams.add("access_token", this.token);
            requestParams.add("v", this.apiVersion);
        }
        return requestParams;
    }

    public UriComponents buildUri(String action, VkEvent event) {
        MultiValueMap<String, String> uri =  combineParams(action, event);
        return UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(this.host)
                .queryParams(uri)
                .path("/method/messages.send")
                .build()
                .encode();
    }
}
