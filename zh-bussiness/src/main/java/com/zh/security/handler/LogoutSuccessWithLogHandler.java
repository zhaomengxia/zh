package com.zh.security.handler;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zh.api.Result;
import com.zh.entity.log.ActEvtLog;
import com.zh.entity.test2.ZRoles;
import com.zh.entity.test2.ZUser;
import com.zh.enums.ExceptionEnum;
import com.zh.security.util.SecurityUtil;
import com.zh.service.log.ActEvtLogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

/**
 * 登出成功处理器
 **/
@Component
public class LogoutSuccessWithLogHandler implements LogoutSuccessHandler {

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private ActEvtLogService operateLogService;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        long start = System.currentTimeMillis();
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        //另启一个线程记录日志
        ThreadUtil.execute(() -> {
            //获取用户信息
            ZUser sysUser = (ZUser) authentication.getPrincipal();
            //获取当前登陆用户ip
            String ip = SecurityUtil.getIpAddress(request);
            operateLogService.save(ActEvtLog.builder()
                    .operator(sysUser.getName())
                    //多角色用","隔开
                    .operatorRole(sysUser.getRoles().parallelStream().map(ZRoles::getRoleName).collect(Collectors.joining(StrUtil.COMMA)))
                    .operateTime(start)
                    .operatorIp(ip)
                    .operateActive("登出")
                    .build());
        });
        response.getWriter().write(objectMapper.writeValueAsString(Result.success(ExceptionEnum.LOGOUT_SUCCESS)));
    }
}
