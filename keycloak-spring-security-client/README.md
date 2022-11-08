# Spring Security & Keycloak整合

> 基于`Spring Boot`基础环境实现`Spring Security`与`Keycloak`认证中心的整合，实现登录回调访后访问受保护的资源。

当我们访问受保护的资源地址时，如：[http://localhost:8080/customer](http://localhost:8080/customer)，会跳转到`Keycloak`授权页面，地址为：`http://localhost:10000/realms/minbox/protocol/openid-connect/auth?response_type=code&client_id=example-learning&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Fcustomer&state=16e5739e-dfea-401c-9688-598d4b6e0575&login=true&scope=openid`



输入用户名以及密码登录成功后会跳转到`redirect_uri`参数的目标地址，这时我们就可以访问权限允许范围内受保护的资源了。