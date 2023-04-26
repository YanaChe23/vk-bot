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
	private WebClient webclient;
	private UriComponents uriComponents;
	private WebClient getWebclient() {
		if(webclient == null) webclient = WebClient.create();
		return webclient;
	}
	public void makeRequest(ApiMethod method, String action, VkEvent event) {

		// todo перенести наверх?
		WebClient client = getWebclient();
		uriComponents = uri.buildUri(action, event);
		switch(method) {
			case POST:
				sendRequest(uriComponents, client.post());
				break;
			case GET:
				sendRequest(uriComponents, client.get());
				break;
		}
	}
	 public String sendRequest(UriComponents params, WebClient.UriSpec uriSpec){
		 String response  = uriSpec
			.uri(params.toString())
			.retrieve()
			.onStatus(status -> status.isError(),
					resp -> Mono.error(new ServiceException("Something went wrong. Please try later", resp.statusCode().value())))
			.bodyToMono(String.class)
			.block();
		 return response;
	}

}


