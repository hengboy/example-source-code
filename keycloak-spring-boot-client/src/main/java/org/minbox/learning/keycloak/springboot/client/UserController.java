package org.minbox.learning.keycloak.springboot.client;

import org.keycloak.adapters.spi.KeycloakAccount;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户控制器
 *
 * @author 恒宇少年
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping("/info")
    public String getUserInfo() {
        return "this is user info.";
    }
}
