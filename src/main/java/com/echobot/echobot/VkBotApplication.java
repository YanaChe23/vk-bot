package com.echobot.echobot;

import com.echobot.echobot.params.Uri;
import com.echobot.echobot.requests.Request;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class VkBotApplication {
	public static Request request;
	public static Uri uri;
	//public static WebClient webclient = WebClient.create();

	public static void main(String[] args) {
		SpringApplication.run(VkBotApplication.class, args);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				SpringConfig.class
		);
		request = context.getBean("request", Request.class);
		uri = context.getBean("uri", Uri.class);
	}
}
