package com.redisgeek.functions.redis.leapahead.hash;

import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Component
public class HrandFieldWithValues implements Function<Map<String,String>, List<Map.Entry<String, String>>> {
        private final StringRedisConnection stringRedisConnection;
        public HrandFieldWithValues(StringRedisConnection stringRedisConnection){
            this.stringRedisConnection = stringRedisConnection;
        }
        @Override
        public List<Map.Entry<String, String>> apply(Map<String,String> input) {
            return stringRedisConnection.hRandFieldWithValues(input.get("key"),
                            Long.parseLong(input.get("count")));
        }
}