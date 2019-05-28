package com.hendisantika.springbootfeaturetoggle.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-feature-toggle
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-05-28
 * Time: 22:23
 */
@Configuration
public class RedisConfig {

    @Value("${redisHost}")
    private String redisHost = "";


    @Bean
    public JedisPool getPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);

        // Tests whether connections are dead during idle periods
        poolConfig.setTestWhileIdle(true);

        poolConfig.setMaxWaitMillis(120000);

        return new JedisPool(poolConfig, redisHost);
    }
}