package com.echobot.echobot.requests;

import com.echobot.echobot.events.newMessage.VkEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import reactor.core.publisher.Mono;

@Component
public class Request {
	@Autowired
	Uri uri;
	private WebClient webClient = getWebclient();
	private UriComponents uriComponents;
	private WebClient getWebclient() {
		if(webClient == null) webClient = WebClient.create();
		return webClient;
	}
	public void makeRequest(ApiMethod method, String action, VkEvent event) {
		uriComponents = uri.buildUri(action, event);
		switch(method) {
			case POST:
				sendRequest(uriComponents, webClient.post());
				break;
			case GET:
				sendRequest(uriComponents, webClient.get());
				break;
		}
	}

	public String sendRequest(UriComponents params, WebClient.UriSpec uriSpec){
		//return uriSpec
		String test = uriSpec
			.uri(params.toString())
			.retrieve()
			.onStatus(status -> status.isError(),
					resp -> Mono.error(new ServiceException("Something went wrong. Please try later", resp.statusCode().value())))
			.bodyToMono(String.class)
			.block();
		System.out.println(params);
		System.out.println(test);
		return test;
	}

}


