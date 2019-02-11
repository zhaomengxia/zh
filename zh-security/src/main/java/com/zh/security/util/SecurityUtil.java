package com.zh.security.util;

import cn.hutool.core.util.StrUtil;
import com.zh.exceptions.UnifiedException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * spring security 相关帮助工具类
 *
 * @author  赵梦霞
 * @since 2018-12-17 16:26

 **/
@SuppressWarnings("unused")
public class SecurityUtil {

    private SecurityUtil() {
    }

    /**
     * @author  赵梦霞
     * @Description 获取当前登陆用户信息
     * @since 2018/12/17 16:28
     **/
    public static Object getCurrentUserInfo() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * @param token
     * @author  赵梦霞
     * @Description 根据token获取用户的认证信息
     * @since 2018/12/12 14:44
     **/
    public static Authentication getAuthentication(FindByIndexNameSessionRepository repository, String token) throws UnifiedException {
        Session session = repository.findById(token);
        if (session == null) {
            throw new UnifiedException("websocket连接 session过期或者失效");
        }
        SecurityContextImpl securityContext = session.getAttribute("SPRING_SECURITY_CONTEXT");
        return securityContext.getAuthentication();
    }

    /**
     * @author  赵梦霞
     * @Description 获取当前用户ip
     * @since 2018/12/17 17:15
     **/
    public static String getCurrentUserUrl() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        return getIpAddress(requestAttributes.getRequest());
    }

    /**
     * @param request 请求对象
     * @author  赵梦霞
     * @Description 获取请求ip
     * @since 2018/12/17 17:12
     **/
    private static String getIpAddress(HttpServletRequest request) {
        String xIp = request.getHeader("X-Real-IP");
        String xFor = request.getHeader("X-Forwarded-For");
        if (StringUtils.isNotEmpty(xFor) && !"unKnown".equalsIgnoreCase(xFor)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = xFor.indexOf(",");
            if (index != -1) {
                return xFor.substring(0, index);
            } else {
                return xFor;
            }
        }
        xFor = xIp;
        if (StrUtil.isNotEmpty(xFor) && !"unKnown".equalsIgnoreCase(xFor)) {
            return xFor;
        }
        if (StrUtil.isBlank(xFor) || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("Proxy-Client-IP");
        }
        if (StrUtil.isBlank(xFor) || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StrUtil.isBlank(xFor) || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StrUtil.isBlank(xFor) || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StrUtil.isBlank(xFor) || "unknown".equalsIgnoreCase(xFor)) {
            xFor = request.getRemoteAddr();
        }
        return xFor;
    }


}
