package org.minbox.learning.resource.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户资源控制器
 *
 * @author 恒宇少年
 */
@RequestMapping(value = "/user")
@RestController
public class UserController {

    @GetMapping(value = "/info")
    public String getInfo() {
        return "this is user info.";
    }
}
