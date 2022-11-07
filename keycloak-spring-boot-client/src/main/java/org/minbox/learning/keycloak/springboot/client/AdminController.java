package org.minbox.learning.keycloak.springboot.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 管理员控制器
 *
 * @author 恒宇少年
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @GetMapping
    public String index() {
        return "this is admin index.";
    }
}
