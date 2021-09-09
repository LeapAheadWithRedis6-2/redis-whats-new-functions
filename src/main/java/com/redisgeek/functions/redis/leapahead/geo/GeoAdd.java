package com.redisgeek.functions.redis.leapahead.geo;

import org.springframework.data.geo.Point;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.function.Function;

@Component
public class GeoAdd implements Function<Map<String,String>, Long> {
        private final StringRedisTemplate redisTemplate;
        public GeoAdd(StringRedisTemplate redisTemplate){
            this.redisTemplate = redisTemplate;
        }
        @Override
        public Long apply(Map<String,String> input) {
            Point p = new Point(Double.parseDouble(input.get("long")),
                    Double.parseDouble(input.get("lat")));
            return redisTemplate.opsForGeo().add(input.get("k"),p,input.get("m"));
        }
}
