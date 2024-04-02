package com.echobot.echobot.uribuilders;

import com.echobot.echobot.events.newmessage.VkEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;


@Component
abstract class BaseVkResponseUriBuilder implements VkResponseUriBuilder {
    @Value("${request.token}")
    protected String token;
    @Value("${request.apiVersion}")
    protected String apiVersion;
    @Value("${request.host}")
    protected String host;
    @Value("${request.scheme}")
    protected String scheme;

    public String buildUri(VkEvent vkEvent) {
        return UriComponentsBuilder.newInstance()
                .scheme(scheme)
                .host(this.host)
                .queryParams(getParams(vkEvent))
                .path(getPath())
                .build()
                .toString();
    }

    protected abstract MultiValueMap<String, String> getParams(VkEvent vkEvent);

    protected abstract String getPath();

    protected MultiValueMap<String, String> getBaseParams() {
        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("access_token", this.token);
        params.add("v", this.apiVersion);
        return params;
    }
}