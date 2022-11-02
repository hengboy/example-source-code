package org.minbox.learning.resource.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 资源服务器配置类
 *
 * @author 恒宇少年
 */
@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
@Slf4j
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
                // 自定义异常处理
                .exceptionHandling(c ->
                        // 无权限访问
                        c.accessDeniedHandler((request, response, accessDeniedException) -> {
                            log.info("无权限访问", accessDeniedException);
                            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                            response.setContentType(MediaType.APPLICATION_JSON.toString());
                            response.getWriter().write("{\"code\":403,\"msg\":\"无权限访问\"}");
                        }))
                // 启用资源服务器
                .oauth2ResourceServer(oauth2 -> oauth2.jwt());
        return http.build();
    }
}
