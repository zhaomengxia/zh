package com.zh.security.properties.web;

import lombok.Data;
import org.springframework.stereotype.Component;


/**
 * web配置类
 *
 * @author  hahaha
 * @since 2018-11-12 9:35

 **/
@Component
@Data
public class WebSecurityProperties {

    /**
     * 忽略的url
     */
    private String[] ignore = {"/**/*"};

    /**
     * 验证表单的url
     */
    private String loginProcessingUrl = "/authentication/login";

    /**
     * 登出的url
     */
    private String logoutUrl = "/authentication/logout";

    /**
     * 权限校验表达式
     */
    private String access = "@permissionService.hasPermission(request,authentication)";
}
