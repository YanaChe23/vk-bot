package com.echobot.echobot;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@SpringBootTest
@ContextConfiguration(classes = VkBotApplicationTests.class,
		loader = AnnotationConfigContextLoader.class)
class VkBotApplicationTests {
	@Test
	void contextLoads() {
	}
}
