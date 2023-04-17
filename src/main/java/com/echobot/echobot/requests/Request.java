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
	private static WebClient webclient;
	private static WebClient getWebclient() {
		if(webclient == null) webclient = WebClient.create();
		return webclient;
	}
	public void makeRequest(ApiMethod method, String action, VkEvent event) {
		WebClient client = getWebclient();
		UriComponents uriComponents = uri.buildUri(action, event);
		switch(method) {
			case POST:
				sendPostRequest(uriComponents, client.post());
				break;
			case GET:
				sendPostRequest(uriComponents, client.get());
				break;
		}
	}
	 public void sendPostRequest(UriComponents params, WebClient.UriSpec uriSpec){
		 String test = uriSpec
		.uri(params.toString())
		.retrieve()
		.onStatus(status -> status.isError(),
				response -> Mono.error(new ServiceException("Something went wrong. Please try later", response.statusCode().value())))
		.bodyToMono(String.class)
		.block();
		 System.out.println(test);
	}

}


