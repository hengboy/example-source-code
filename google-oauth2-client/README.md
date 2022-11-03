## Google OAuth2 & OIDC 单点登录

`Spring Security OAuth2 Client`整合对接`Google`提供的`OAuth2`服务 ，支持OIDC协议。

### 申请Google OAuth2应用

申请地址：[https://console.cloud.google.com/apis/dashboard](https://console.cloud.google.com/apis/dashboard)。

1. 创建项目
2. 创建OAuth应用
3. 创建OAuth2客户端凭证

### 修改IDEA JVM Options

由于Google服务访问不到，需要配置JVM使用系统的代理服务，添加`-Djava.net.useSystemProxies=true`。

> 需要配置GOOGLE_CLIENT_ID、GOOGLE_CLIENT_SECRET两个环境变量。