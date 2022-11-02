package org.minbox.learning.github.oauth2.client.web;

import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author 恒宇少年
 */
@RestController
public class IndexController {
    @GetMapping
    public String index(Principal principal) {
        OAuth2AuthenticationToken authenticationToken = (OAuth2AuthenticationToken) principal;
        OAuth2User user = authenticationToken.getPrincipal();
        String name = user.getAttribute("name");
        String account = user.getAttribute("login");
        return "GitHub OAuth2登录成功，欢迎：" + name + "(" + account + ")" + ",访问首页.";
    }
}
