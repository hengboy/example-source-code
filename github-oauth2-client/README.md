## 对接GitHub提供的OAuth2单点登录

### 注册GitHub OAuth2 应用

要使用 GitHub 的 OAuth 2.0 身份验证系统进行登录，您必须注册一个新的 [OAuth 应用程序](https://github.com/settings/applications/new)。

注册 OAuth 应用程序时，确保授权回调 URL 设置为 `http://127.0.0.1:8080/login/oauth2/code/github-idp`。

`Spring OAuth2 Client`对`GitHub`进行了默认支持，提供了默认的`provider`而且值必须为`github`，详情见：`org.springframework.security.config.oauth2.client.CommonOAuth2Provider`。

> 启动项目之前需要配置本地的环境变量`GITHUB_CLIENT_ID`、`GITHUB_CLIENT_SECRET`。
