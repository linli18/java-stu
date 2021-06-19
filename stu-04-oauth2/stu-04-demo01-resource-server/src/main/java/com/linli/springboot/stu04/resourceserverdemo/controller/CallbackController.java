package com.linli.springboot.stu04.resourceserverdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.OAuth2ClientProperties;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class CallbackController {
    @Autowired
    private OAuth2ClientProperties oauth2ClientProperties;

    @Value("${security.oauth2.access-token-uri}")
    private String accessTokenUri;

    @GetMapping("/callback")
    public OAuth2AccessToken login(@RequestParam("code") String code) {
        // 创建 AuthorizationCodeResourceDetails 对象
        AuthorizationCodeResourceDetails authorizationCodeResourceDetails = new AuthorizationCodeResourceDetails();
        authorizationCodeResourceDetails.setAccessTokenUri(accessTokenUri);
        authorizationCodeResourceDetails.setClientId(oauth2ClientProperties.getClientId());
        authorizationCodeResourceDetails.setClientSecret(oauth2ClientProperties.getClientSecret());
        // 创建 OAuth2RestTemplate 对象
        OAuth2RestTemplate oAuth2RestTemplate = new OAuth2RestTemplate(authorizationCodeResourceDetails);
        // 设置code
        oAuth2RestTemplate.getOAuth2ClientContext().getAccessTokenRequest().setAuthorizationCode(code);
        // 通过这个方式，设置 redirect_uri 参数
        oAuth2RestTemplate.getOAuth2ClientContext().getAccessTokenRequest().setPreservedState("http://127.0.0.1:9090/callback");
        oAuth2RestTemplate.setAccessTokenProvider(new AuthorizationCodeAccessTokenProvider());
        return oAuth2RestTemplate.getAccessToken();
    }

}
