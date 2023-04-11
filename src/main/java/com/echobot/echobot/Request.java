package com.echobot.echobot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponents;

@Component
public class Request {
	WebClient webclient;
	@Value("${request.host}")
	String url;
	public Request() {
		 this.webclient = WebClient.create();
	}

	public void makeRequest(Method method, UriComponents params) {
		switch(method) {
			case POST:
				sendPostRequest(params);
				break;
			case GET:
				System.out.println("Ouch");
		}
	}

	public void sendPostRequest(UriComponents params) {
		WebClient.ResponseSpec response =  this.webclient.post()
				.uri(params.toString())
				.retrieve();
	System.out.println(response.bodyToMono(String.class).block());
	}


}

enum Method {
	POST,
	GET
}
