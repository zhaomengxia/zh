package com.zh.security.sevice;

import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

/**
 * 权限校验接口
 **/
public interface Permission {

    /**
     * @param request        请求对象
     * @param authentication 认证对象
     * @return 能否访问
     * @author hahaha
     * @Description 拦截登陆用户所能访问的url
     * @since 2018/11/8 14:06
     **/
    boolean hasPermission(HttpServletRequest request, Authentication authentication);

}
