package org.minbox.learning.resource.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * 资源服务器配置类
 *
 * @author 恒宇少年
 */
@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class ResourceServerConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize ->
                        // 配置 "/users/**" 下的地址需要授权 "user.userInfo"
                        authorize.mvcMatchers("/user/**").hasAuthority("SCOPE_user.userInfo")
                                // 配置"/messages/**"下的地址需要授权"message.read"
                                .mvcMatchers("/message/**").hasAuthority("SCOPE_message.read")
                                .anyRequest().authenticated()
                )
                // 启用资源服务器
                .oauth2ResourceServer(oauth2 -> oauth2.jwt());
        return http.build();
    }
}
