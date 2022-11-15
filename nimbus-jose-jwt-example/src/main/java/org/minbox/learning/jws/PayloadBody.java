package org.minbox.learning.jws;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author 恒宇少年
 */
@Data
@Accessors(chain = true)
public class PayloadBody {
    /**
     * 主题
     */
    private String sub;

    /**
     * 签发时间
     */
    private Long iat;

    /**
     * 过期时间
     */
    private Long exp;

    /**
     * JWT的ID
     */
    private String jti;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户拥有的权限
     */
    private List<String> authorities;
}
