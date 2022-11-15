package org.minbox.learning.jws.jose;

import com.nimbusds.jose.jwk.RSAKey;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

/**
 * @author 恒宇少年
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Jwks {
    /**
     * 生成RSA Key
     *
     * @return {@link RSAKey}
     */
    public static RSAKey generateRsa() {
        KeyPair keyPair = KeyPairGeneratorUtils.generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        // @formatter:off
        return new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();
        // @formatter:on
    }

}
