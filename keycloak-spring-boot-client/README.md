## SpringBoot整合Keycloak
> 使用Keycloak提供的依赖`keycloak-spring-boot-starter`，可以直接实现`SpringBoot`与`Keycloak`的整合。
>
> 使用文档详见：https://www.keycloak.org/docs/latest/securing_apps/index.html#_spring_boot_adapter



### 配置Keycloak

#### 添加客户端

创建名为`example-learning`的客户端，不启用安全认证。

客户端添加完成后在列表再次点击`example-learning`编辑客户端的`Root URL`为`http://localhost:8080`、`Valid redirect URIS`（授权回调地址）为`http://localhost:8080/*`。

#### 添加角色

添加名为`user`的角色

#### 添加用户

添加名为`yuqiyu`的用户，添加完成后进行进行设置密码并绑定`user`角色。

### 配置SpringBoot

在`application.yml`配置文件中添加相关配置：

```
keycloak:
  # 是否为开放的客户端，开放的客户端不需要配置秘钥
  public-client: true
  # 客户端所属的领域
  realm: minbox
  # keycloak服务地址
  auth-server-url: http://localhost:10000
  # 客户端ID
  resource: example-learning
  security-constraints:
    # 配置admin角色所访问的地址列表
    - auth-roles:
        - admin
      security-collections:
        - patterns:
            - /admin
            - /admin/*
    # 配置user角色所访问的地址列表
    - auth-roles:
        - user
      security-collections:
        - name: common user
          patterns:
            - /user/info
```



### 访问测试

访问[http://localhost:8080/user/info](http://localhost:8080/user/info)会自动跳转到`Keycloak`的登录页面，输入用户名、密码后根据客户端配置的回调地址，会自动跳转到`http://localhost:8080/user/info`。

