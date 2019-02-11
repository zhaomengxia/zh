package com.zh.security.handler;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zh.api.Result;
import com.zh.entity.log.OperateLog;
import com.zh.entity.sys.SysRoles;
import com.zh.entity.sys.SysUser;
import com.zh.enums.ExceptionEnum;
import com.zh.security.util.SecurityUtil;
import com.zh.service.log.OperateLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Instant;
import java.util.stream.Collectors;

/**
 * 登出成功处理器
 *
 * @author  赵梦霞
 * @since 2018-12-24 16:02

 **/
@Component
public class LogoutSuccessWithLogHandler implements LogoutSuccessHandler {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private OperateLogService operateLogService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        SysUser sysUser;
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        //无认证信息说明用户session已过期
        if (authentication == null) {
            response.getWriter().write(objectMapper.writeValueAsString(Result.success(ExceptionEnum.TOKEN_INVALID)));
        } else {
            //获取当前登陆用户ip
            String ip = SecurityUtil.getCurrentUserUrl();
            sysUser = (SysUser) authentication.getPrincipal();
            operateLogService.save(OperateLog.builder()
                    .operator(sysUser.getName())
                    //多角色用","隔开
                    .operateRole(sysUser.getRoles().parallelStream().map(SysRoles::getRoleName).collect(Collectors.joining(StrUtil.COMMA)))
                    .operateTime(Instant.now().toEpochMilli())
                    .operatorIp(ip)
                    .operateActive("登出")
                    .build());
        }
        response.getWriter().write(objectMapper.writeValueAsString(Result.success(ExceptionEnum.LOGOUT_SUCCESS)));
    }
}
