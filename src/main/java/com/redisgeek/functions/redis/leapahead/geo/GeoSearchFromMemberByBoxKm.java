package com.redisgeek.functions.redis.leapahead.geo;

import io.lettuce.core.dynamic.annotation.Key;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.domain.geo.GeoReference;
import org.springframework.data.redis.domain.geo.GeoShape;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;

@Component
public class GeoSearchFromMemberByBoxKm implements Function<Map<String, String>, Long> {
    private final RedisTemplate<String, String> redisTemplate;

    public GeoSearchFromMemberByBoxKm(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Long apply(Map<String, String> input) {
        return redisTemplate
                .opsForGeo()
                .searchAndStore(input.get("k"),
                        input.get("destK"),
                        GeoReference.fromMember(input.get("m")),
                        GeoShape.byBox(Double.parseDouble(input.get("width")),
                                Double.parseDouble(input.get("height")),
                                RedisGeoCommands.DistanceUnit.KILOMETERS),
                        RedisGeoCommands.GeoSearchStoreCommandArgs.newGeoSearchStoreArgs().sortAscending());
    }
}