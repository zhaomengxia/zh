package com.zh.security.permission.config;

import com.zh.security.permission.DefaultPermission;
import com.zh.security.permission.Permission;
import com.zh.security.userdetails.DefaultUserDetailsService;
import com.zh.security.userdetails.UserDetailsServiceExpansion;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 默认配置类
 *
 * @author  hahaha
 * @since 2018-12-14 10:58

 **/
@Configuration
public class DefaultConfig {

    /**
     * @author  hahaha
     * @Description 密码编码器
     * @since 2018/12/14 14:36
     **/
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    @ConditionalOnMissingBean(UserDetailsService.class)
    public UserDetailsServiceExpansion userDetailsService() {
        return new DefaultUserDetailsService();
    }

    @Bean(name = "permissionService")
    @ConditionalOnMissingBean(Permission.class)
    public Permission permission() {
        return new DefaultPermission();
    }

}
