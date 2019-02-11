package com.zh.security.sms.service;

/**
 * 短信发送接口
 *
 * @author  赵梦霞
 * @date 2018-12-12 15:14

 **/
public interface CodeSender {

    /**
     * @author  赵梦霞
     * @since 2018/12/12 15:15
     * @param mobile 手机号码
     * @param code 验证码
     * @Description 发送验证码
     **/
    void send(String mobile,String code);

}
