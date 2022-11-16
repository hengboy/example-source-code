package org.minbox.learning.jws;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import org.minbox.framework.util.JsonUtils;
import org.minbox.learning.jws.jose.Jwks;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Jws示例
 *
 * @author 恒宇少年
 * @see JWSAlgorithm
 */
public class JwsExample {
    /**
     * 创建{@link PayloadBody}
     *
     * @return
     */
    static PayloadBody createPayloadBody() {
        return new PayloadBody()
                .setUsername("hengboy")
                .setJti(UUID.randomUUID().toString())
                .setIat(System.currentTimeMillis())
                .setExp(3600L)
                .setAuthorities(new ArrayList<>() {{
                    add("user");
                }});
    }

    /**
     * rsa加密方式
     *
     * @throws Exception
     */
    static void rsa256() throws Exception {
        RSAKey rsaKey = Jwks.generateRsa();
        // @formatter:off
        JWSHeader jwsHeader = new JWSHeader.Builder(JWSAlgorithm.RS256)
                .type(JOSEObjectType.JWT)
                .build();
        // @formatter:on

        JWSSigner signer = new RSASSASigner(rsaKey);

        PayloadBody payloadBody = createPayloadBody();
        JWSObject jwsObject = new JWSObject(jwsHeader, new Payload(JsonUtils.toJsonString(payloadBody)));
        jwsObject.sign(signer);
        String s = jwsObject.serialize();

        System.out.println("加密字符串：" + s);

        // To parse the JWS and verify it, e.g. on client-side
        jwsObject = JWSObject.parse(s);

        RSAKey publicRsaKey = rsaKey.toPublicJWK();
        System.out.println("解密公钥：" + publicRsaKey);

        JWSVerifier verifier = new RSASSAVerifier(publicRsaKey);
        boolean isVerifier = jwsObject.verify(verifier);
        System.out.println("是否解密成功：" + isVerifier);
        System.out.println("解密后的字符串：" + jwsObject.getPayload().toString());
    }

    /**
     * HS256加密方式
     * <p>
     * 加密字符串：
     * eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOm51bGwsImlhdCI6MTY2ODQ5ODYzNDgxNywiZXhwIjozNjAwLCJqdGkiOiI2NWNkZTdjZC00NjRmLTQwZTgtYWQ3Ny03ZGE4YWVlODdmY2YiLCJ1c2VybmFtZSI6Imhlbmdib3kiLCJhdXRob3JpdGllcyI6WyJ1c2VyIl19.lp01HXW-b_mV0OwkcMsHzaKRcpS1X4bIVqxmbX7BBjs
     *
     * @throws Exception
     */
    static void hs256() throws Exception {
        // Generate random 256-bit (32-byte) shared secret
        SecureRandom random = new SecureRandom();
        byte[] sharedSecret = new byte[32];
        random.nextBytes(sharedSecret);

        System.out.println("256位的密钥：" + Bit.bytesToBit(sharedSecret));

        // Create HMAC signer
        JWSSigner signer = new MACSigner(sharedSecret);

        PayloadBody payloadBody = createPayloadBody();
        // @formatter:off
        JWSHeader jwsHeader =
                new JWSHeader.Builder(JWSAlgorithm.HS256)
                        .type(JOSEObjectType.JWT)
                        .build();
        // @formatter:on
        JWSObject jwsObject = new JWSObject(jwsHeader, new Payload(JsonUtils.toJsonString(payloadBody)));

        // Apply the HMAC
        jwsObject.sign(signer);

        // To serialize to compact form, produces something like
        String s = jwsObject.serialize();

        System.out.println("加密字符串：" + s);

        // To parse the JWS and verify it, e.g. on client-side
        jwsObject = JWSObject.parse(s);

        JWSVerifier verifier = new MACVerifier(sharedSecret);

        boolean isVerifier = jwsObject.verify(verifier);
        System.out.println("是否解密成功：" + isVerifier);
        System.out.println("解密后的字符串：" + jwsObject.getPayload().toString());
    }


    public static void main(String[] args) throws Exception {
        hs256();
    }


}
