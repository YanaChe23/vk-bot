package com.echobot.echobot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.echobot.echobot")
@PropertySource("classpath:application.properties")
public class SpringConfig {
}
