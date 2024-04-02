package com.echobot.echobot.generators;

import lombok.extern.apachecommons.CommonsLog;
import org.springframework.stereotype.Component;

@Component
public class RandomIdGeneratorImpl implements RandomIdGenerator {
    @Override
    public String generate() {
        return String.valueOf(System.currentTimeMillis());
    }
}
