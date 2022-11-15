## nimbus-jose-jwt-example

`nimbus-jose-jwt`提供的`JWT（JSON Web Token）`规范的`JWS（JSON Web Signature）`的实现方式中，核心类如下所示：

- **JWSHeader**：生成JWS的头信息
- **Payload**：生成JWS的主体信息
- **JWSObject**：JWS安全对象实例，使用`#serialize()`方法可以生成加密字符串，使用`#parse()`方法可以将加密字符串转换为`JWSObject`。
- **JWSSigner**：JWS签名者，加密时使用，如：`RSASSASigner`、`MACSigner`
- **JWSVerifier**：JWS验证者，解密时使用，如：`RSASSAVerifier`、`MACSigner`



### 生成RSA所需的jwt.jks文件

可以使用`keytool`命令生成，如下所示：

```bash
keytool -genkey -alias jwt -keyalg RSA -keystore jwt.jks
```

> 注意：密钥对（KeyPair）也可以使用`java.security.KeyPairGenerator`生成，这种方式就不无需生成`jwt.jks`文件了，详情查看源码：`org.minbox.learning.jws.jose.Jwks`、`org.minbox.learning.jws.jose.KeyPairGeneratorUtils`。
