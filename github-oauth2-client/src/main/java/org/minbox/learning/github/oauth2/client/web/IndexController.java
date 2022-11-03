package org.minbox.learning.github.oauth2.client.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 恒宇少年
 */
@RestController
public class IndexController {
    @GetMapping
    public String index(@AuthenticationPrincipal OAuth2User oAuth2User) {
        String name = oAuth2User.getAttribute("name");
        String account = oAuth2User.getAttribute("login");
        return "GitHub OAuth2登录成功，欢迎：" + name + "(" + account + ")" + ",访问首页.";
    }
}
