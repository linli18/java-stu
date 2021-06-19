package com.linli.springboot.stu04.authorizationserverdemo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer //声明开启 OAuth 授权服务器的功能
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * 设置 /oauth/check_token 端点，通过认证后可访问
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //这里的认证，指的是使用 client-id + client-secret 进行的客户端认证，不要和用户认证混淆。
        ///oauth/check_token 端点对应 CheckTokenEndpoint 类，用于校验访问令牌的有效性
        security.checkTokenAccess("isAuthenticated()");
    }

    /**
     * 进行 Client 客户端的配置
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.
                //设置使用基于内存的 Client 存储器。实际情况下，最好放入数据库中，方便管理。
                inMemory()
                //创建一个 Client 配置。
                .withClient("clientapp").secret("112233")
                .authorizedGrantTypes("authorization_code") //授权码模式
                .redirectUris("http://127.0.0.1:9090/callback") //配置回调地址，选填
                .scopes("read_userinfo", "read_contacts") //可授权的Scope
//                .and().withClient()       //可继续配置新的client
                  ;
    }

    /**
     * 配置使用的 AuthenticationManager 实现用户认证的功能
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }
}
