package com.echobot.echobot;

import com.echobot.echobot.requests.Uri;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {
    @Bean
    public Uri uri() {
        return new Uri();
    }

}
