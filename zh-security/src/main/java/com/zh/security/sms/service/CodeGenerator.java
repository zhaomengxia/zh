package com.zh.security.sms.service;

import com.zh.security.sms.code.Code;

/**
 * 验证码生成接口
 *
 * @author  赵梦霞
 * @date 2018-12-12 15:06

 **/
public interface CodeGenerator {

    /**
     * @author  赵梦霞
     * @since 2018/12/12 15:07
     * @param mobile 手机号码
     * @Description 生成验证码
     **/
    Code generator(String mobile);

}
