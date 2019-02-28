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
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
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
        ////当请求通过HTTPS发生时，Spring Security会自动加入一个secure标识到XSRF-TOKENcookie 。Spring Security对于CSRF cookie不使用SameSite=strict 的标志，但它在使用Spring Session或WebFlux会话处理时会使用，
        // 这对会话cookie有意义，因为它有助于识别用户，但是没有为CSRF cookie提供太多价值，因为CSRF令牌也需要在请求中
//        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());//开启csrf防护

        //手机验证码登陆配置
        http.apply(smsAuthenticationSecurityConfig);
        //Spring Security * 默认情况下不添加CSP（内容安全策略）是一个增加的安全层，可帮助缓解CSS（跨站点脚本）和数据注入攻击
        //要启用该策略，需要配置应用程序以返回Content-Security-Policy标题。可以在 HTML页面中<meta http-equiv="Content-Security-Policy">使用标记
        //spring安全性默认提供了许多安全标头
//        http.headers().contentSecurityPolicy("script-src 'self' https://trustedscripts.example.com; object-src https://trustedplugins.example.com; report-uri /csp-report-endpoint/");

        //Spring Boot应用程序中强制使用HTTPS
//        http.requiresChannel().requiresSecure();

    }

}
