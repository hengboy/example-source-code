package org.minbox.learning.jws.jose;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

/**
 * 密钥对生成工具类
 *
 * @author 恒宇少年
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KeyPairGeneratorUtils {
    /**
     * 生成RSA加密方式的密钥对
     *
     * @return {@link KeyPair}
     */
    static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }
}
