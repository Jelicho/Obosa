package com.ssafy.obosa.config;

import com.ssafy.obosa.model.domain.Auction;
import com.ssafy.obosa.model.domain.Product;
import com.ssafy.obosa.model.domain.User;
import com.ssafy.obosa.service.common.RedisService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig
{
    @Bean
    public RedisConnectionFactory redisConnectionFactory()
    {
        LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory();
        return lettuceConnectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate()
    {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Product.class));
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(User.class));
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(Auction.class));
        return redisTemplate;
    }

    @Bean
    MessageListenerAdapter messageListenerAdapter()
    {
        return new MessageListenerAdapter(new RedisService());
    }

    @Bean
    RedisMessageListenerContainer redisContainer()
    {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisConnectionFactory());
        container.addMessageListener(messageListenerAdapter(), topic());
        return container;
    }

    @Bean
    ChannelTopic topic()
    {
        return new ChannelTopic("Event");
    }
}
