package com.zh.security.sms.service;

/**
 * 短信发送接口
 *
 * @author  hahaha
 * @date 2018-12-12 15:14

 **/
public interface CodeSender {

    /**
     * @author  hahaha
     * @since 2018/12/12 15:15
     * @param mobile 手机号码
     * @param code 验证码
     * @Description 发送验证码
     **/
    void send(String mobile,String code);

}
