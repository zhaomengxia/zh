package com.zh.security.config.web;

import com.zh.security.handler.DefaultLogoutSuccessHandler;
import com.zh.security.handler.DefaultLoginFailureHandler;
import com.zh.security.handler.DefaultLoginSuccessHandler;
import com.zh.security.properties.SecurityProperties;
import com.zh.security.sms.config.SmsAuthenticationSecurityConfig;
import com.zh.security.sms.filter.VerificationFilter;
import com.zh.security.strategy.DefaultInvalidSessionStrategy;
import com.zh.security.userdetails.UserDetailsServiceExpansion;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;

import javax.annotation.Resource;

/**
 * spring security 配置类
 *
 * @author  hahaha
 * @since 2018-11-01 14:52

 **/
@Configuration
@EnableWebSecurity
@SuppressWarnings("unchecked")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @ConditionalOnMissingBean(value = AuthenticationSuccessHandler.class)
    public AuthenticationSuccessHandler successHandler() {
        return new DefaultLoginSuccessHandler();
    }

    @Bean
    @ConditionalOnMissingBean(value = AuthenticationFailureHandler.class)
    public AuthenticationFailureHandler failureHandler() {
        return new DefaultLoginFailureHandler();
    }

    @Bean
    @ConditionalOnMissingBean(value = InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy() {
        return new DefaultInvalidSessionStrategy();
    }

    @Bean
    @ConditionalOnMissingBean(value = LogoutSuccessHandler.class)
    public LogoutSuccessHandler logoutSuccessHandler() {
        return new DefaultLogoutSuccessHandler();
    }

    @Resource
    private InvalidSessionStrategy invalidSessionStrategy;

    @Resource
    private LogoutSuccessHandler logoutSuccessHandler;

    @Resource
    private SecurityProperties securityProperties;

    @Resource
    private AuthenticationSuccessHandler successHandler;

    @Resource
    private AuthenticationFailureHandler failureHandler;

    @Resource
    private UserDetailsServiceExpansion userDetailsServiceExpansion;

    @Resource
    private SpringSessionRememberMeServices rememberMeServices;

    @Bean
    public SmsAuthenticationSecurityConfig smsAuthenticationSecurityConfig() {
        SmsAuthenticationSecurityConfig smsAuthenticationSecurityConfig = new SmsAuthenticationSecurityConfig();
        smsAuthenticationSecurityConfig.setAuthenticationSuccessHandler(successHandler);
        smsAuthenticationSecurityConfig.setAuthenticationFailureHandler(failureHandler);
        smsAuthenticationSecurityConfig.setUserDetailsService(userDetailsServiceExpansion);
        return smsAuthenticationSecurityConfig;
    }

    @Resource
    private SmsAuthenticationSecurityConfig smsAuthenticationSecurityConfig;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        VerificationFilter verificationFilter = new VerificationFilter();
        verificationFilter.setAuthenticationFailureHandler(failureHandler);
        verificationFilter.setRedisTemplate(redisTemplate);

        http.addFilterBefore(verificationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers(securityProperties.getWeb().getIgnore()).permitAll()
                .anyRequest()
                .access(securityProperties.getWeb().getAccess())
                .and()
                // session管理
                .sessionManagement()
                .maximumSessions(securityProperties.getSession().getMaximumSessions())
                .maxSessionsPreventsLogin(securityProperties.getSession().getNotAllowLogin())
                .and()
                .invalidSessionStrategy(invalidSessionStrategy)
                .and()
                .formLogin()
                //.loginPage("/authentication/require")
                .loginProcessingUrl(securityProperties.getWeb().getLoginProcessingUrl())
                .permitAll()
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .and()
                .logout()
                .logoutUrl(securityProperties.getWeb().getLogoutUrl())
                .permitAll()
                .logoutSuccessHandler(logoutSuccessHandler)
                .and()
                .rememberMe()
                .rememberMeServices(rememberMeServices)
                .and()
                .csrf().disable();// 关闭csrf防护
        //手机验证码登陆配置
        http.apply(smsAuthenticationSecurityConfig);
    }

}
