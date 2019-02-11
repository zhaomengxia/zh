package com.zh.security.sms.config;

import com.zh.security.sms.filter.SmsAuthenticationFilter;
import com.zh.security.sms.provider.SmsAuthenticationProvider;
import com.zh.security.userdetails.UserDetailsServiceExpansion;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 手机号验证码登陆配置类
 *
 * @author  hahaha
 * @since 2018-12-12 13:41

 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class SmsAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private AuthenticationSuccessHandler authenticationSuccessHandler;
    private AuthenticationFailureHandler authenticationFailureHandler;
    private UserDetailsServiceExpansion userDetailsService;

    @Override
    public void configure(HttpSecurity http) {

        SmsAuthenticationFilter smsAuthenticationFilter = new SmsAuthenticationFilter();
        //注入AuthenticationManager对象
        smsAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        //设置登陆成功/登陆失败处理器
        smsAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        smsAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        SmsAuthenticationProvider smsAuthenticationProvider = new SmsAuthenticationProvider();
        //注入userDetailsService实现类
        smsAuthenticationProvider.setUserDetailsService(userDetailsService);

        //配置 provider 以及过滤器
        http.authenticationProvider(smsAuthenticationProvider)
                .addFilterAfter(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }

}
