package com.hendisantika.springbootfeaturetoggle.controller;

import com.hendisantika.springbootfeaturetoggle.config.AvailableFeatures;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.manager.FeatureManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-feature-toggle
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-05-28
 * Time: 22:27
 */
@RestController
@RequestMapping("/debug")
public class DebugController {

    private final FeatureManager featureManager;

    public DebugController(FeatureManager featureManager) {
        this.featureManager = featureManager;
    }

    @GetMapping("/checkFeature")
    public Map<String, Object> checkFeatureToggle() {
        return new HashMap<String, Object>() {{
            put("(1) is the secret feature enabled?", featureManager.isActive(AvailableFeatures.ENABLE_SECRET_FEATURE));
            put("(2) is the secret feature enabled?", AvailableFeatures.ENABLE_SECRET_FEATURE.isActive());
        }};
    }
}
