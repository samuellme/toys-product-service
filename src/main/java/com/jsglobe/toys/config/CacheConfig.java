package com.jsglobe.toys.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import static java.time.Duration.ofSeconds;

@EnableCaching
@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager(
            RedisCacheConfiguration redisCacheConfiguration,
            RedisConnectionFactory redisConnectionFactory
    ) {
        return RedisCacheManager
                .RedisCacheManagerBuilder
                .fromConnectionFactory(redisConnectionFactory)
                .withCacheConfiguration("product", redisCacheConfiguration)
                .build();
    }

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration(
            @Value("${cache.expiration_in_seconds}") long ttl_in_seconds
    ) {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(ofSeconds(ttl_in_seconds));
    }
}
