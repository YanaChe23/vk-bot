package com.echobot.echobot.requests;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;

import static com.echobot.echobot.VkBotApplication.webclient;

@Component
public class Request {
	public void makeRequest(ApiMethod method, UriComponents params) {
		switch(method) {
			case POST:
				sendPostRequest(params, createPostWebClient());
				break;
			case GET:
				sendPostRequest(params, createGetWebClient());
		}
	}
	public void sendPostRequest(UriComponents params, WebClient.UriSpec uriSpec) {
		WebClient.ResponseSpec response =  uriSpec
				.uri(params.toString())
				.retrieve();
		System.out.println(response.bodyToMono(String.class).block());
	}

	public WebClient.RequestBodyUriSpec createPostWebClient() {
		return webclient.post();
	}

	public WebClient.RequestHeadersUriSpec<?> createGetWebClient(){
		return webclient.get();
	}

}


