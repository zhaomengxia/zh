package com.zh.security.permission;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限默认校验器
 *
 * @author  hahaha
 * @since 2018-12-14 10:56

 **/
public class DefaultPermission implements Permission {

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        //全部放过
        return true;
    }
}
