## SpringSecurity整合新版本Authorization Server

基于`Spring Security 5.x`以及最新版本的[authorization-server](https://github.com/spring-projects/spring-authorization-server)实现。

## 运行示例

### 1. 启动本地数据库

```bash
# 启动mariadb
docker run --name mariadb-server -p 3306:3306 -e MYSQL_ROOT_PASSWORD=123456 -d mariadb:latest --character-set-server=utf8mb4 --collation-server=utf8mb4_general_ci
```

**初始化表结构**：

```sql
create table oauth2.oauth2_authorization
(
    id                            varchar(100)                            not null
        primary key,
    registered_client_id          varchar(100)                            not null,
    principal_name                varchar(200)                            not null,
    authorization_grant_type      varchar(100)                            not null,
    authorized_scopes             varchar(1000)                           null,
    attributes                    text                                    null,
    state                         varchar(500)                            null,
    authorization_code_value      text                                    null,
    authorization_code_issued_at  timestamp default current_timestamp()   not null on update current_timestamp(),
    authorization_code_expires_at timestamp default '0000-00-00 00:00:00' not null,
    authorization_code_metadata   text                                    null,
    access_token_value            text                                    null,
    access_token_issued_at        timestamp default '0000-00-00 00:00:00' not null,
    access_token_expires_at       timestamp default '0000-00-00 00:00:00' not null,
    access_token_metadata         text                                    null,
    access_token_type             varchar(100)                            null,
    access_token_scopes           varchar(1000)                           null,
    oidc_id_token_value           text                                    null,
    oidc_id_token_issued_at       timestamp default '0000-00-00 00:00:00' not null,
    oidc_id_token_expires_at      timestamp default '0000-00-00 00:00:00' not null,
    oidc_id_token_metadata        text                                    null,
    refresh_token_value           text                                    null,
    refresh_token_issued_at       timestamp default '0000-00-00 00:00:00' not null,
    refresh_token_expires_at      timestamp default '0000-00-00 00:00:00' not null,
    refresh_token_metadata        text                                    null
);

create table oauth2.oauth2_authorization_consent
(
    registered_client_id varchar(100)  not null,
    principal_name       varchar(200)  not null,
    authorities          varchar(1000) not null,
    primary key (registered_client_id, principal_name)
);

create table oauth2.oauth2_registered_client
(
    id                            varchar(100)                            not null
        primary key,
    client_id                     varchar(100)                            not null,
    client_id_issued_at           timestamp default current_timestamp()   not null,
    client_secret                 varchar(200)                            null,
    client_secret_expires_at      timestamp default '0000-00-00 00:00:00' not null,
    client_name                   varchar(200)                            not null,
    client_authentication_methods varchar(1000)                           not null,
    authorization_grant_types     varchar(1000)                           not null,
    redirect_uris                 varchar(1000)                           null,
    scopes                        varchar(1000)                           not null,
    client_settings               varchar(2000)                           not null,
    token_settings                varchar(2000)                           not null
);
```



### 2. 启动授权服务器

```bash
cd authorization-server
mvn clean spring-boot:run
```

启动端口号：9000

### 3. 启动资源服务器

```bash
cd resource-server
mvn clean spring-boot:run
```

启动端口号：10000

### 4. 启动客户端

```bash
cd application-client
mvn clean spring-boot:run
```

启动端口号：8080

### 5. 访问

访问[http://127.0.0.1:8080](http://127.0.0.1:8080)客户端地址时会自动跳转到授权服务器的登录页面`http://127.0.0.1:9000/login`，输入用户名：`yuqiyu`，密码：`123456`后会将`code`回调到`http://127.0.0.1:8080/authorized`，根据`code`从授权服务器获取`AccessToken`返回。

### 6. 获取令牌

```json
{"access_token":"eyJraWQiOiIyZTM0MTZhNi03NGY5LTQ2ZDMtOGI3ZC03NDAyM2ZmZGEwMjUiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ5dXFpeXUiLCJhdWQiOiJjc2RuIiwibmJmIjoxNjY3MzcyNzE1LCJzY29wZSI6WyJ1c2VyLnVzZXJJbmZvIl0sImlzcyI6Imh0dHA6XC9cLzEyNy4wLjAuMTo5MDAwIiwiZXhwIjoxNjY3Mzc2MzE1LCJpYXQiOjE2NjczNzI3MTV9.AmLhfvEdWsUbLzQ4GpIkbiTOuHzCQu5hJ0Al_-NpcjsNJDjyxTVKlRYIxrsdLlWrqopW01vZ6Ci6Qt-pvKIh_TfCER-R1CvqTL_t4xe8uBBLQ-Oea11vvpK7U27amO1etW-CG-DK_DGWOX6syP7HgaNbvEtsCZ6taPr8ogMiES7Cmp14hzdK9mnzw6_OgkfIvslW5O0e4-4v80o3Jch_87KYCvfxUf-_TC-kOzg_yDAXkoEPbGgdbSHqSfcB6yDeTQMmdctpAjYKXXpIMji-3yPsKyIRG3qxrbvsNgoN3SxUnhRxm51H7ANuplWEro8WbeT6fUNH56nA-90Pi1_uCQ","refresh_token":"T0DkRSMD5dmG8ELbsGCfYVdHoYb2pksbydJTY_YQEDkQzjBWggVtMcipxfqiieB8bzloWrZ0WjQyeOqwc4il2pnerda0685vD3GTZDprgDSEenKyi7RhfMJFaaQHS-_C","scope":"user.userInfo","token_type":"Bearer","expires_in":3599}
```

### 7. 访问资源

```bash
curl -H 'Authorization: Bearer eyJraWQiOiIyZTM0MTZhNi03NGY5LTQ2ZDMtOGI3ZC03NDAyM2ZmZGEwMjUiLCJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ5dXFpeXUiLCJhdWQiOiJjc2RuIiwibmJmIjoxNjY3MzcyNzE1LCJzY29wZSI6WyJ1c2VyLnVzZXJJbmZvIl0sImlzcyI6Imh0dHA6XC9cLzEyNy4wLjAuMTo5MDAwIiwiZXhwIjoxNjY3Mzc2MzE1LCJpYXQiOjE2NjczNzI3MTV9.AmLhfvEdWsUbLzQ4GpIkbiTOuHzCQu5hJ0Al_-NpcjsNJDjyxTVKlRYIxrsdLlWrqopW01vZ6Ci6Qt-pvKIh_TfCER-R1CvqTL_t4xe8uBBLQ-Oea11vvpK7U27amO1etW-CG-DK_DGWOX6syP7HgaNbvEtsCZ6taPr8ogMiES7Cmp14hzdK9mnzw6_OgkfIvslW5O0e4-4v80o3Jch_87KYCvfxUf-_TC-kOzg_yDAXkoEPbGgdbSHqSfcB6yDeTQMmdctpAjYKXXpIMji-3yPsKyIRG3qxrbvsNgoN3SxUnhRxm51H7ANuplWEro8WbeT6fUNH56nA-90Pi1_uCQ' http://127.0.0.1:10000/user/info

this is user info.
```

