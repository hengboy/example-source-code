package org.minbox.learning.jws;

import com.nimbusds.jose.jwk.RSAKey;
import org.minbox.framework.util.JsonUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * 使用SpringSecurity提供的{@link KeyStoreKeyFactory}来生成RSA Key
 * <p>
 * 生成jwt.jks文件命令：
 * keytool -genkey -alias jwt -keyalg RSA -keystore jwt.jks
 *
 * @author 恒宇少年
 */
public class KeyStoreKeyFactoryTest {

    static RSAKey generateRsaKey() {
        // 生成时输入的密码
        char[] password = "123456".toCharArray();
        // classpath:/jwt.jks -> resources/jwt.jks
        String jksFilePath = "jwt.jks";
        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource(jksFilePath), password);
        // alias必须跟生成时保持一致
        KeyPair keyPair = keyStoreKeyFactory.getKeyPair("jwt");
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        return new RSAKey.Builder(publicKey).privateKey(privateKey).build();
    }

    public static void main(String[] args) {
        RSAKey rsaKey = generateRsaKey();
        System.out.println("使用jwt.jks文件获取的RSA Key：" + JsonUtils.beautifyJson(rsaKey));
    }
}
