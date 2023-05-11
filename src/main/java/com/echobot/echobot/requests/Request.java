package com.echobot.echobot.requests;

import com.echobot.echobot.events.newmessage.VkEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import reactor.core.publisher.Mono;

@Component
public class Request {
	@Autowired
	private Uri uri;
	private WebClient webClient = getWebclient();

	private WebClient getWebclient() {
		if (webClient == null) webClient = WebClient.create();
		return webClient;
	}
	public void makeRequest(ApiMethod method, String action, VkEvent event) {
		UriComponents uriComponents = uri.buildUri(action, event);
		switch (method) {
			case POST -> sendRequest(uriComponents, webClient.post());
			case GET -> sendRequest(uriComponents, webClient.get());
		}
	}

	public String sendRequest(UriComponents params, WebClient.UriSpec uriSpec) {
		return uriSpec
			.uri(params.toString())
			.retrieve()
			.onStatus(HttpStatusCode::isError,
					resp -> Mono.error(new ServiceException("Something went wrong. Please try later", resp.statusCode().value())))
			.bodyToMono(String.class)
			.block();
	}
}


