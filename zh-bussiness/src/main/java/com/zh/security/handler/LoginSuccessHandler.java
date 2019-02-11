package com.zh.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.dto.login.UserLoginDTO;
import com.zh.entity.sys.SysResources;
import com.zh.entity.sys.SysRoles;
import com.zh.entity.sys.SysUser;
import com.zh.enums.ExceptionEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 登陆成功处理器
 *
 * @author  赵梦霞
 * @since 2018-11-09 9:48

 **/
@Component
@Slf4j
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    @Log(desc = "登陆")
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        SysUser principal = (SysUser) authentication.getPrincipal();
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        BeanUtils.copyProperties(principal, userLoginDTO);
        HttpSession session = request.getSession();
        userLoginDTO.setToken(session.getId());

        List<SysRoles> roles = principal.getRoles();
        List<SysResources> list = Lists.newArrayList();
        for (SysRoles role : roles) {
            List<SysResources> resources = role.getResources();
            list.addAll(resources);
        }
        Set<SysResources> collect = new LinkedHashSet<>(list).stream().sorted(Comparator.nullsLast(Comparator.comparing(SysResources::getId))).collect(Collectors.toCollection(LinkedHashSet::new));
        userLoginDTO.setSysResources(collect);


        //打印token 方便调试
        log.info("登陆成功，token------>[{}]", session.getId());
        response.getWriter()
                .write(objectMapper.writeValueAsString(Result.success(ExceptionEnum.LOGIN_SUCCESS, userLoginDTO)));
    }


}
