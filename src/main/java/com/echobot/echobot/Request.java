package com.echobot.echobot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
@Component
public class Request {
	WebClient webclient;
	@Value("${request.url}")
	String url;
	public Request() {
		 this.webclient = WebClient.create();
	}

	public void makeRequest(Method method, String action) {
		switch(method) {
			case POST:
				sendPostRequest(action);
				break;
			case GET:
				System.out.println("Ouch");
		}
	}

	private String generatePath(String action) {
		String generatedURL = url + action;
		return generatedURL;
	}

	public void sendPostRequest(String action) {
		String uri = generatePath(action);
//		WebClient.ResponseSpec response =  this.webclient.post()
//				.uri("https://api.vk.com/method/messages.send?user_id=74598334&random_id=1000011&domain=naomh_padraig&chat_id=11&message=test+test+test&dont_parse_links=0&disable_mentions=0&access_token=vk1.a.r51Hp0lRNo-tj0Lb0hUp579lVnlVRDtfmGmbX-GUthgBM9mCcP5xQYV2jtQQQ2q7qA6Gud2XuNBRYX6NM4ETtFd1uc-GnB7nI1o9wYMULuuImcnphPfYDcOIxbHXNKLwD1iaG6V4QVtZXG24RB6v2DKjwLiFEKiZvp-WqrIIU9cGeUsVJi5F3fat7g12rImqduXzEAStdIZ6XZhhROkRBw&v=5.131")
//				.retrieve();
//	System.out.println(response.bodyToMono(String.class).block());
		System.out.println(uri);
	}
}

enum Method {
	POST,
	GET
}
