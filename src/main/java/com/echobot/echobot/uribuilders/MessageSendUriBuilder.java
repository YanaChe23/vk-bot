package com.echobot.echobot.uribuilders;

import com.echobot.echobot.events.newmessage.Message;
import com.echobot.echobot.events.newmessage.VkEvent;
import com.echobot.echobot.exceptions.EventParsingException;
import com.echobot.echobot.generators.RandomIdGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class MessageSendUriBuilder extends BaseVkResponseUriBuilder {
    private final String path = "/method/messages.send";
    private final String groupChatMessagePrefix = "[club";
    private final RandomIdGenerator randomIdGenerator;

    protected MultiValueMap<String, String> getParams(VkEvent vkEvent) {
        Message message = Optional.ofNullable(vkEvent.getObject().getMessage())
                .orElseThrow(() -> new EventParsingException("Failed to get a message."));
        MultiValueMap<String, String> params = getBaseParams();
        params.add("peer_id", message.getPeer_id());
        params.add("random_id", randomIdGenerator.generate());
        params.add("message", getText(message));
        return params;
    }

    private String getText(Message message)  {
        String textToEcho = Optional.ofNullable(message.getText())
                .orElseThrow(() -> new EventParsingException("Failed to get a message text."));

        if (textToEcho.isEmpty())
            return URLEncoder.encode("If you send me a written text I echo it.", StandardCharsets.UTF_8);

        if (textToEcho.startsWith(groupChatMessagePrefix)) textToEcho = extractTextForEchoing(textToEcho);
        return URLEncoder.encode("You said: " + textToEcho, StandardCharsets.UTF_8);
    }

    private String extractTextForEchoing(String text) {
        Pattern pattern = Pattern.compile("](.*)");
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            text = matcher.group(1).trim();
        }
        
        return text;
    }

    protected String getPath() {
        return path;
    }
}