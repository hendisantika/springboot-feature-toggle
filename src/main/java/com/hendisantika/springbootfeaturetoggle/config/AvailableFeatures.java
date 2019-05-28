package com.hendisantika.springbootfeaturetoggle.config;

import org.togglz.core.Feature;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-feature-toggle
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-05-28
 * Time: 22:26
 */
public enum AvailableFeatures implements Feature {
    @Label("Activates the secret feature")
    ENABLE_SECRET_FEATURE;

    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }
}