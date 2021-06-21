package com.linli.springboot.stu04.resourceserverdemo.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableOAuth2Sso //开启SSO功能
public class OAuthSsoConfig {
}
