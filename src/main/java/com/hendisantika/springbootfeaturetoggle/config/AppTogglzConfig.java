package com.hendisantika.springbootfeaturetoggle.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.spi.FeatureProvider;
import org.togglz.core.user.FeatureUser;
import org.togglz.core.user.SimpleFeatureUser;
import org.togglz.core.user.UserProvider;
import redis.clients.jedis.JedisPool;

import java.util.Set;

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

    @Bean
    public UserProvider userProvider() {
        return this::getCurrentUserProvider;
    }

    @Bean
    public StateRepository getStateRepository() {
        return new RedisStateRepository.Builder()
                .keyPrefix(togglzPrefix + ":")
                .jedisPool(jedisPool)
                .build();
    }

    @Bean
    public FeatureProvider featureProvider() {
        return new EnumBasedFeatureProvider(AvailableFeatures.class);
    }

    private FeatureUser getCurrentUserProvider() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication == null) {
            return null;
        }
        // try to obtain the name of this user
        String name = getName(authentication);
        // check for the authority for feature admins
        Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        boolean featureAdmin = isFeatureAdmin(authentication, authorities);

        SimpleFeatureUser user = new SimpleFeatureUser(name, featureAdmin);
        user.setAttribute(USER_ATTRIBUTE_ROLES, authorities);
        return user;
    }

    private String getName(Authentication authentication) {
        if (authentication.getPrincipal() == null) {
            return "unknown_user";
        }
        return authentication.getPrincipal().toString();
    }

    private boolean isFeatureAdmin(Authentication authentication, Set<String> authorities) {
//        return authorities.contains("SUPER_ADMIN");
        return true;
    }
}
