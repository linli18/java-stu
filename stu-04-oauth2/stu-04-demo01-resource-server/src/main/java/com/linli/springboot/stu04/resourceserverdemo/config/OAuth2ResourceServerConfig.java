package com.linli.springboot.stu04.resourceserverdemo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
//声明开启资源服务功能
@EnableResourceServer
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
    /**
     * 设置 HTTP 权限
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //设置/login无需权限访问
                .antMatchers("/login").permitAll()
                //设置回调地址无需权限访问
                .antMatchers("/callback").permitAll()
                //设置回调地址无需权限访问
                .antMatchers("/callback02").permitAll()
                //设置其他请求需要认证后访问
                .anyRequest().authenticated();
    }
}
