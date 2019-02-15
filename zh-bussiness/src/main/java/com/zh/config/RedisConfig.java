package com.zh.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.google.common.collect.Maps;
import com.zh.contants.RedisContant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

/**
 * redis相关配置类
 *
 * @author hahaha
 * @date 2018-08-06 11:22
 **/
@EnableCaching
@Configuration
@Slf4j
@SuppressWarnings("unchecked")
public class RedisConfig {

    /**
     * @return ObjectMapper
     * @author hahaha
     * @date 2018/8/9 14:30
     * @Description 自定义ObjectMapper 通用展示数据
     **/
    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return new ObjectMapper()
                //全局忽略null值序列化
                .setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL)
                //设置本地Locale
                .setLocale(Locale.getDefault())
                .setTimeZone(TimeZone.getDefault())
                .registerModule(javaTimeModule()
                        .addSerializer(Long.class, ToStringSerializer.instance));
    }


    /**
     * @author hahaha
     * @date 2018/8/16 16:34
     * @Description 存储redis转换数据使用
     **/
    @Bean("redisMapper")
    public ObjectMapper redisMapper() {
        return new ObjectMapper()
                .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY)
                .enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL)
                .registerModule(javaTimeModule());
    }

    /**
     * @param factory RedisConnectionFactory redis连接工厂对象
     * @return RedisTemplate
     * @author hahaha
     * @date 2018/8/8 16:25
     * @Description RedisTemplate 通用redisTemplate
     **/
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory factory) {
        //设置序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(redisMapper());
        //配置redisTemplate
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        RedisSerializer stringSerializer = new StringRedisSerializer();
        //key序列化
        redisTemplate.setKeySerializer(stringSerializer);
        //value序列化
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        //Hash key序列化
        redisTemplate.setHashKeySerializer(stringSerializer);
        //Hash value序列化
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * @param factory RedisConnectionFactory redis连接工厂对象
     * @return CacheManager
     * @author hahaha
     * @date 2018/8/8 16:27
     * @Description redis缓存manager 通用manager
     **/
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        return RedisCacheManager.builder(factory)
                //默认缓存配置 缓存时间默认30分钟
                .cacheDefaults(getRedisCacheConfigurationWithTtl(30 * 60))
                //自定义缓存配置
                .withInitialCacheConfigurations(getRedisCacheConfigurationMap())
                .build();
    }

    private Map<String, RedisCacheConfiguration> getRedisCacheConfigurationMap() {
        Map<String, RedisCacheConfiguration> redisCacheConfigurationMap = Maps.newHashMapWithExpectedSize(2);
        //对指定cache进行过期时间配置
        redisCacheConfigurationMap.put(RedisContant.RAIN_DATA_LIST, this.getRedisCacheConfigurationWithTtl(15 * 60));
        redisCacheConfigurationMap.put(RedisContant.WATER_DATA_LIST, this.getRedisCacheConfigurationWithTtl(15 * 60));
        return redisCacheConfigurationMap;
    }

    /**
     * @param seconds 过期时间 单位：秒（s）
     * @author hahaha
     * @Description 配置设置
     * @since 2019/1/24 15:54
     **/
    private RedisCacheConfiguration getRedisCacheConfigurationWithTtl(Integer seconds) {
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        jackson2JsonRedisSerializer.setObjectMapper(redisMapper());
        return RedisCacheConfiguration.defaultCacheConfig()
                //过期时间
                .entryTtl(Duration.ofSeconds(seconds))
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jackson2JsonRedisSerializer))
                .disableCachingNullValues();
    }

    /**
     * @author hahaha
     * @date 2018/8/16 16:39
     * @Description java8 LocalDateTime LocalDate LocalTime 转换方式
     **/
    private SimpleModule javaTimeModule() {
        return new JavaTimeModule()
                .addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")))
                .addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
    }


}
