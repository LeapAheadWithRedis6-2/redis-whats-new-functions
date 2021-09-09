package com.redisgeek.functions.redis.leapahead.hash;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class Hmset implements Function<Map<?,?>, String> {
        private final StringRedisTemplate redisTemplate;
        public Hmset(StringRedisTemplate redisTemplate){
            this.redisTemplate = redisTemplate;
        }
        @Override
        public String apply(Map<?,?> input) {
            redisTemplate.opsForHash().putAll((String)input.get("key")
                    ,(Map<?,?>)input.get("f"));
            return "OK";
        }
}
