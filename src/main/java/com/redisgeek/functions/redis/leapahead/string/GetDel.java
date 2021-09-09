package com.redisgeek.functions.redis.leapahead.string;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GetDel implements Function<String, String> {
    private final StringRedisTemplate redisTemplate;

    public GetDel(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String apply(String input) {
        return redisTemplate
                .opsForValue()
                .getAndDelete(input);
    }
}
