package com.hendisantika.springbootfeaturetoggle.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.manager.FeatureManager;

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
}
