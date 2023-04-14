// todo добавить обработчик ошибок в request
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
	public static WebClient webclient = WebClient.create();

	public static void main(String[] args) {
		SpringApplication.run(VkBotApplication.class, args);
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
				SpringConfig.class
		);
		request = context.getBean("request", Request.class);
		uri = context.getBean("uri", Uri.class);

//		List<Activity> activities = crmClient
//				.get()
//				.uri(uriBuilder -> uriBuilder
//						.path("/activities/search")
//						.queryParam("contactId", contactId)
//						.queryParam("orderBy", "startDate")
//						.queryParam("orderType", "DESC")
//						.build())
//				.header(HttpHeaders.AUTHORIZATION, bearerToken)
//				.retrieve()
//				.bodyToFlux(Activity.class)
//				.collectList()
//				.block();
//
//		try {
//			ObjectMapper objectMapper = new ObjectMapper();
//			System.err.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(activities));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
