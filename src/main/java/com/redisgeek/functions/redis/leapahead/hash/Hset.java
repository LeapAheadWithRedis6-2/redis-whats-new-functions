package com.redisgeek.functions.redis.leapahead.hash;

import lombok.Data;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;

@Component
public class Hset implements Function<Map<String,String>, String> {
        private final StringRedisTemplate redisTemplate;
        public Hset(StringRedisTemplate redisTemplate){
            this.redisTemplate = redisTemplate;
        }
        @Override
        public String apply(Map<String,String> input) {
            redisTemplate.opsForHash().put(input.get("k"),input.get("f"),input.get("v"));
            return "OK";
        }
}
