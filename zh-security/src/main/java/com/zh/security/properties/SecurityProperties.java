package com.zh.security.properties;

import com.zh.security.properties.code.CodeSecurityProperties;
import com.zh.security.properties.session.SessionSecurityProperties;
import com.zh.security.properties.web.WebSecurityProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 配置
 *
 * @author  赵梦霞
 * @date 2018-11-12 9:32

 **/
@Data
@Component
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

    private WebSecurityProperties web = new WebSecurityProperties();

    private SessionSecurityProperties session = new SessionSecurityProperties();

    private CodeSecurityProperties code = new CodeSecurityProperties();
}
