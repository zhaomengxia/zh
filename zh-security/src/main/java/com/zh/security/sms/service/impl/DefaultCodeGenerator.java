package com.zh.security.sms.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.zh.security.properties.SecurityProperties;
import com.zh.security.sms.code.Code;
import com.zh.security.sms.service.CodeGenerator;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 默认验证码生成
 *
 * @author  hahaha
 * @since 2018-12-12 15:18

 **/
public class DefaultCodeGenerator implements CodeGenerator {

    @Resource
    private SecurityProperties securityProperties;
    @Resource
    private RedisTemplate<String, Code> redisTemplate;

    @Override
    public Code generator(String mobile) {
        Code code = new Code(RandomUtil.randomNumbers(securityProperties.getCode().getLength()), securityProperties.getCode().getExpiredTime());
        //验证码存入redis 如果存在先删除
        redisTemplate.delete(mobile);
        redisTemplate.opsForValue().set(mobile, code, securityProperties.getCode().getExpiredTime() + 60, TimeUnit.SECONDS);
        return code;
    }

}
