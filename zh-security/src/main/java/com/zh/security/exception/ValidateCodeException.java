package com.zh.security.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常
 *
 * @author  赵梦霞
 * @since 2018-12-12 17:02

 **/
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = -6202343444976467499L;

    public ValidateCodeException(String msg){
        super(msg);
    }

}
