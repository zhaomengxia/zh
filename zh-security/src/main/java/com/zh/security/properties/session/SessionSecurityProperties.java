package com.zh.security.properties.session;

import lombok.Data;

/**
 * session控制配置类
 *
 * @author  hahaha
 * @since 2018-11-12 9:41

 **/
@Data
public class SessionSecurityProperties {

    /**
     * 允许登陆的最多人数
     */
    private Integer maximumSessions = 1;

    /**
     * 是否不允许后一个人登陆
     */
    private Boolean notAllowLogin = false;

    /**
     * 记住我时间 默认14天
     */
    private Integer rememberTime = 60*60*24*14;

}
