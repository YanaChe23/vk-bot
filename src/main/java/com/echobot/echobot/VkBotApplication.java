// TODO разобраться с пробелами в сообщении
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

//		Request req = context.getBean("request", Request.class);
//
//		//String params = "user_id=74598334&random_id=1000011&domain=naomh_padraig&chat_id=11&message=test+test+test&dont_parse_links=0&disable_mentions=0&access_token=vk1.a.r51Hp0lRNo-tj0Lb0hUp579lVnlVRDtfmGmbX-GUthgBM9mCcP5xQYV2jtQQQ2q7qA6Gud2XuNBRYX6NM4ETtFd1uc-GnB7nI1o9wYMULuuImcnphPfYDcOIxbHXNKLwD1iaG6V4QVtZXG24RB6v2DKjwLiFEKiZvp-WqrIIU9cGeUsVJi5F3fat7g12rImqduXzEAStdIZ6XZhhROkRBw&v=5.131";
//		req.makeRequest(Method.POST, "send.message", params);

	}


}
