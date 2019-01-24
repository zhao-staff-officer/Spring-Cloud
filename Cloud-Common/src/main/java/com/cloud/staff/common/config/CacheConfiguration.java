package com.cloud.staff.common.config;

import java.io.Serializable;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@ConfigurationProperties(prefix = "spring.cache.redis")
public class CacheConfiguration {
    
	private static final Logger log=LoggerFactory.getLogger(CacheConfiguration.class);
	
	private Duration timeToLive = Duration.ZERO;

	public void setTimeToLive(Duration timeToLive) {
		this.timeToLive = timeToLive;
	}

	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		log.info("自定义timeToLive={}",timeToLive);
		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig().entryTtl(this.timeToLive)
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()))
				.disableCachingNullValues();

		RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory).cacheDefaults(config)
				.transactionAware().build();

		log.info("自定义RedisCacheManager加载完成");
		return redisCacheManager;
	}
	@Bean(name = "redisTemplate")
	public RedisTemplate<String, Serializable> redisTemplate(RedisConnectionFactory  redisConnectionFactory) {
		RedisTemplate<String, Serializable> template = new RedisTemplate<>();
		
		template.setKeySerializer(this.keySerializer());
		template.setValueSerializer(this.valueSerializer());
		
		template.setHashKeySerializer(this.keySerializer());
		template.setHashValueSerializer(this.valueSerializer());
		template.setConnectionFactory(redisConnectionFactory);
		log.info("自定义redisTemplate加载完成");
		return template;
	}

	private RedisSerializer<String> keySerializer() {
		return new StringRedisSerializer();
	}

	private RedisSerializer<Object> valueSerializer() {
		return new GenericJackson2JsonRedisSerializer();
	}
	
}
