package org.minbox.learning.keycloak.springsecurity.client.config;

import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Keycloak配置类
 *
 * @author 恒宇少年
 */
@Configuration
public class KeycloakConfig {
    /**
     * keycloak配置加载器
     * <p>
     * 配置后无需提供"keycloak.json"文件
     *
     * @return {@link KeycloakSpringBootConfigResolver}
     */
    @Bean
    public KeycloakConfigResolver keycloakConfigResolver() {
        return new KeycloakSpringBootConfigResolver();
    }

}
