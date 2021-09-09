package com.redisgeek.functions.redis.leapahead.hash;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;

@Component
public class HgetAll implements Function<String, Map<?,?>> {
        private final RedisTemplate redisTemplate;
        public HgetAll(RedisTemplate redisTemplate){
            this.redisTemplate = redisTemplate;
        }
        @Override
        public Map<?,?> apply(String input) {
            return redisTemplate.opsForHash().entries(input);
        }
}