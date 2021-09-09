package com.redisgeek.functions.redis.leapahead.string;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;
import java.util.function.Function;

@Component
public class GetEx implements Function<Map<String,String>, String> {
    private final StringRedisTemplate redisTemplate;

    public GetEx(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String apply(Map<String,String> input) {
        Duration t = Duration.ofSeconds(Long.parseLong(input.get("value")));
        return redisTemplate
                .opsForValue()
                .getAndExpire(input.get("key"), t);
    }
}
