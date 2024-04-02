package com.echobot.echobot.generators;

import org.springframework.stereotype.Component;

@Component
public class RandomIdGeneratorImpl implements RandomIdGenerator {
    @Override
    public String generate() {
        return String.valueOf(System.currentTimeMillis());
    }
}
