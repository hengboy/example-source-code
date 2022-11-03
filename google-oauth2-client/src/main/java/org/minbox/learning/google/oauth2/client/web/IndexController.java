package org.minbox.learning.google.oauth2.client.web;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 首页控制器
 *
 * @author 恒宇少年
 */
@RestController
public class IndexController {
    /**
     * 登录成功后跳转的首页
     *
     * @param oidcUser {@link org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser}
     * @return
     */
    @GetMapping
    public OidcUser getUserInfo(@AuthenticationPrincipal OidcUser oidcUser) {
        return oidcUser;
    }
}
