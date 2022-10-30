package org.minbox.learning.application.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 恒宇少年
 */
@RestController
public class WelcomeController {
    @GetMapping
    public String welcome() {
        return "欢迎您登录系统.";
    }
}
