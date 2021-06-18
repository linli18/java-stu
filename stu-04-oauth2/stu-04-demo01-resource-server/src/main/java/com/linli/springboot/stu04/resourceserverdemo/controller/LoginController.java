package com.linli.springboot.stu04.resourceserverdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class LoginController {
    @Autowired
    private OAuth2ClientProperties oauth2ClientProperties;

    @Value("${security.oauth2.access-token-uri}")
    private String accessTokenUri;

    /**
     * 在 /login 接口中，资源服务器扮演的是一个 OAuth 客户端的角色，调用授权服务器的 /oauth/token 接口，使用密码模式进行授权，获得访问令牌。
     * @param username
     * @param password
     * @return
     */
    @PostMapping ("/login")
    public OAuth2AccessToken login(@RequestParam("username") String username,
                                   @RequestParam("password") String password) {
        //创建 ResourceOwnerPasswordResourceDetails 对象，填写密码模式授权需要的请求参数。
        ResourceOwnerPasswordResourceDetails resourceOwnerPasswordResourceDetails = new ResourceOwnerPasswordResourceDetails();
        resourceOwnerPasswordResourceDetails.setAccessTokenUri(accessTokenUri);
        resourceOwnerPasswordResourceDetails.setClientId(oauth2ClientProperties.getClientId());
        resourceOwnerPasswordResourceDetails.setClientSecret(oauth2ClientProperties.getClientSecret());
        resourceOwnerPasswordResourceDetails.setUsername(username);
        resourceOwnerPasswordResourceDetails.setPassword(password);
        //创建 OAuth2RestTemplate 对象，它是 Spring Security OAuth 封装的工具类，用于请求授权服务器。
        //OAuth2RestTemplate 是有状态的工具类，所以需要每次都重新创建。
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceOwnerPasswordResourceDetails);
        //同时，将 ResourceOwnerPasswordAccessTokenProvider 设置到其中，表示使用密码模式授权。
        restTemplate.setAccessTokenProvider(new ResourceOwnerPasswordAccessTokenProvider());
        //调用 OAuth2RestTemplate 的 #getAccessToken() 方法，调用授权服务器的 /oauth/token 接口，进行密码模式的授权
        return restTemplate.getAccessToken();
    }
}
