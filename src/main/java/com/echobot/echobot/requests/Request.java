package com.echobot.echobot.requests;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;
import reactor.core.publisher.Mono;

@Component
public class Request {
	private static WebClient webclient;
	private static WebClient getWebclient() {
		if(webclient == null) webclient = WebClient.create();
		return webclient;
	}
	public void makeRequest(ApiMethod method, UriComponents params) {
		WebClient client = getWebclient();
		switch(method) {
			case POST:
				sendPostRequest(params, client.post());
				break;
			case GET:
				sendPostRequest(params, client.get());
				break;
		}
		sendPostRequest(params, webclient.get());
	}
	 public void sendPostRequest(UriComponents params, WebClient.UriSpec uriSpec){
		 String test = uriSpec
		.uri(params.toString())
		.retrieve()
		.onStatus(status -> status.isError(),
				response -> Mono.error(new ServiceException("Something went wrong. Please try later", response.statusCode().value())))
		.bodyToMono(String.class)
		.block();
		 //System.out.println(test);
	}
}


