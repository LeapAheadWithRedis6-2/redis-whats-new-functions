package com.redisgeek.functions.redis.leapahead.string;

import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Component
public class SetExAt implements Function<Map<String,String>, String> {
    private final RedisTemplate redisTemplate;

    public SetExAt(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String apply(Map<String,String> input) {
        Objects.requireNonNull(redisTemplate.getConnectionFactory())
                .getConnection()
                .set(input.get("key").getBytes(StandardCharsets.UTF_8),
                        input.get("value").getBytes(StandardCharsets.UTF_8),
                        Expiration.unixTimestamp(Long.parseLong(input.get("e")), TimeUnit.SECONDS),
                        RedisStringCommands.SetOption.UPSERT);
        return "OK";
    }
}
