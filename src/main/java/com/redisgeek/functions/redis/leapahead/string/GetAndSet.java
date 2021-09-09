package com.redisgeek.functions.redis.leapahead.string;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;

@Component
public class GetAndSet implements Function<Map<String,String>, String> {
    private final StringRedisTemplate redisTemplate;

    public GetAndSet(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String apply(Map<String,String> input) {
        return redisTemplate
                .opsForValue()
                .getAndSet(input.get("key"), input.get("value"));
    }
}
