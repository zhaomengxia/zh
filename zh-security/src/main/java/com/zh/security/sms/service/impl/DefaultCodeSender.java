package com.zh.security.sms.service.impl;

import com.zh.security.sms.service.CodeSender;
import lombok.extern.slf4j.Slf4j;

/**
 * 默认验证码发送
 *
 * @author  hahaha
 * @since 2018-12-12 15:20

 **/
@Slf4j
public class DefaultCodeSender implements CodeSender {

    @Override
    public void send(String mobile, String code) {
        log.info("手机号码[{}]验证码[{}]发送成功", mobile, code);
    }
}
