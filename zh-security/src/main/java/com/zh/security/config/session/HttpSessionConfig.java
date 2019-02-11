package com.zh.security.config.session;

import com.zh.security.properties.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.security.web.authentication.SpringSessionRememberMeServices;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

import javax.annotation.Resource;

/**
 *
 * session 配置类
 *
 * @author  赵梦霞
 * @date 2018-08-16 12:00

 **/
@Configuration
public class HttpSessionConfig {

    @Resource
    private SecurityProperties securityProperties;

    /**
     * @author  赵梦霞
     * @since 2018/8/27 10:17
     * @Description session策略配置 header 头部信息
     * @return
     **/
    @Bean
    public HttpSessionIdResolver httpSessionIdResolver(){
        return new HeaderHttpSessionIdResolver("token");
    }

    /**
     * @author  赵梦霞
     * @since 2018/11/13 14:43
     * @Description 记住我
     **/
    @Bean
    public SpringSessionRememberMeServices rememberMeServices(){
        SpringSessionRememberMeServices rememberMeServices = new SpringSessionRememberMeServices();
        rememberMeServices.setValiditySeconds(securityProperties.getSession().getRememberTime());
        return rememberMeServices;
    }

}
