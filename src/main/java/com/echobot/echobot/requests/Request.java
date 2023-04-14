package com.echobot.echobot.requests;

import com.echobot.echobot.CustomBadRequestException;
import com.echobot.echobot.events.newMessage.Resp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponents;
import reactor.core.publisher.Mono;

import static com.echobot.echobot.VkBotApplication.webclient;

@Component
public class Request {
	public void makeRequest(ApiMethod method, UriComponents params) throws JsonProcessingException {
		switch(method) {
			case POST:
				sendPostRequest(params, webclient.delete());
				break;
			case GET:
				//sendPostRequest(params, webclient.get());
				break;
		}
	}
	 public void sendPostRequest(UriComponents params, WebClient.UriSpec uriSpec) throws JsonProcessingException {
		//WebClient.ResponseSpec response = uriSpec
		//Resp responses =
				uriSpec
		.uri(params.toString())
				.retrieve()
				//.onStatus(status-> status.value() == HttpStatus.I_AM_A_TEAPOT.value(),
				.onStatus(status -> !status.is2xxSuccessful(),
						response -> Mono.error(new ServiceException("Method not allowed. Please check the URL.", response.statusCode().value())))
				.bodyToMono(Resp.class)
				.block();
		ObjectMapper objectMapper = new ObjectMapper();
		//System.err.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responses));
		//System.out.println(response.bodyToMono(String.class).block());


	}

}


