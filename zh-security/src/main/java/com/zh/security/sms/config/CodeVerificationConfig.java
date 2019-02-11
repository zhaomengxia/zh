package com.zh.security.sms.config;

import com.zh.security.sms.service.CodeGenerator;
import com.zh.security.sms.service.CodeSender;
import com.zh.security.sms.service.impl.DefaultCodeGenerator;
import com.zh.security.sms.service.impl.DefaultCodeSender;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 验证码默认实现提供包含：
 * 验证码生成类
 * 验证码发送类
 *
 * @author  hahaha
 * @since 2018-12-12 15:23

 **/
@Configuration
public class CodeVerificationConfig {

    @Bean
    @ConditionalOnMissingBean(CodeGenerator.class)
    public CodeGenerator generator(){
        return new DefaultCodeGenerator();
    }

    @Bean
    @ConditionalOnMissingBean(CodeSender.class)
    public CodeSender sender(){
        return new DefaultCodeSender();
    }

}
