package com.zh.security.properties.code;

import lombok.Data;

/**
 * 验证码相关配置类
 *
 * @author  赵梦霞
 * @since 2018-12-13 11:46

 **/
@Data
public class CodeSecurityProperties {

    /**
     * 验证码长度 默认4位
     */
    private Integer length = 4;

    /**
     * 过期时间（单位：s） 默认2分钟
     */
    private Integer expiredTime = 120;

}
