package com.echobot.echobot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class VkBotApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(VkBotApplication.class, args);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				SpringConfig.class
		);
		Request req = context.getBean("request", Request.class);
		req.makeRequest(Method.POST, "send.message");

	}


}
