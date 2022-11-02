package org.minbox.learning.application.client;

import cn.hutool.http.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 恒宇少年
 */
@RestController
public class AuthorizedController {
    @Autowired
    private OAuth2ClientProperties oAuth2ClientProperties;

    /**
     * 授权码回调方法
     *
     * @param code 授权码
     * @return
     */
    @GetMapping("/authorized")
    public String authorized(String code) {
        // 根据授权码获取AccessToken
        OAuth2ClientProperties.Registration registration = oAuth2ClientProperties.getRegistration().get("hengboy-authorization-code");
        OAuth2ClientProperties.Provider provider = oAuth2ClientProperties.getProvider().get("hengboy");
        Map<String, Object> params = new HashMap<>();
        params.put("grant_type", "authorization_code");
        params.put("redirect_uri", registration.getRedirectUri());
        params.put("code", code);

        String accessTokenResponse = HttpUtil.createPost(provider.getTokenUri())
                .basicAuth(registration.getClientId(), registration.getClientSecret())
                .form(params).execute().body();
        return accessTokenResponse;
    }
}
