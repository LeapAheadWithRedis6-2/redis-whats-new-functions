package com.redisgeek.functions.redis.leapahead.hash;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class HrandField implements Function<Map<String,String>, List<String>> {
        private final StringRedisConnection stringRedisConnection;
        public HrandField(StringRedisConnection stringRedisConnection){
            this.stringRedisConnection = stringRedisConnection;
        }
        @Override
        public List<String> apply(Map<String,String> input) {
            return stringRedisConnection.hRandField(input.get("key"),
                            Long.parseLong(input.get("count")));
        }
}