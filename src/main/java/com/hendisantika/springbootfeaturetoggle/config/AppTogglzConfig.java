package com.hendisantika.springbootfeaturetoggle.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-feature-toggle
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-05-28
 * Time: 22:22
 */

@Configuration
public class AppTogglzConfig {
    private static final String USER_ATTRIBUTE_ROLES = "roles";
    private final JedisPool jedisPool;
    private final String togglzPrefix;

    public AppTogglzConfig(JedisPool jedisPool,
                           @Value("${togglzPrefix}") String togglzPrefix) {
        this.jedisPool = jedisPool;
        this.togglzPrefix = togglzPrefix;
    }


}
