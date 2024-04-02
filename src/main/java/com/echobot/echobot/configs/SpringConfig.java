package com.echobot.echobot.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.http.HttpClient;

@Configuration
@ComponentScan("com.echobot.echobot")
@PropertySource("classpath:application.properties")
public class SpringConfig {

    @Bean
    public HttpClient httpClient() {
        return HttpClient.newHttpClient();
    }

}
