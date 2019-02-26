package com.zh.security.handler;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.dto.user.RoleResourceDTO;
import com.zh.dto.user.UserLoginDTO;
import com.zh.entity.test2.ZResources;
import com.zh.entity.test2.ZRoles;
import com.zh.entity.test2.ZUser;
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
 **/
@Component
@Slf4j
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    @Log(desc = "登陆")
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        ZUser principal = (ZUser) authentication.getPrincipal();
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        UserLoginDTO userLoginDTO = new UserLoginDTO();
        BeanUtils.copyProperties(principal, userLoginDTO);
        HttpSession session = request.getSession();
        userLoginDTO.setToken(session.getId());

        List<ZRoles> roles = principal.getRoles();
        List<ZResources> list = Lists.newArrayList();
        for (ZRoles role : roles) {
            List<ZResources> resources = role.getResources();
            list.addAll(resources);
        }
        Set<ZResources> collect = new LinkedHashSet<>(list).stream().sorted(Comparator.nullsLast(Comparator.comparing(ZResources::getId))).collect(Collectors.toCollection(LinkedHashSet::new));
        userLoginDTO.setSysResources(collect);
        List<ZResources> resources = collect.stream().filter(a -> StrUtil.isBlank(a.getHttpPath())).collect(Collectors.toList());
        List<RoleResourceDTO> roleResourceDTOS = Lists.newArrayList();
        resources.forEach(a -> {
            RoleResourceDTO roleResourceDTO = new RoleResourceDTO();
            BeanUtils.copyProperties(a, roleResourceDTO);
            roleResourceDTO.setHasPermission(a.getHasPersission());
            roleResourceDTOS.add(roleResourceDTO);
        });

        RoleResourceDTO roleResourceDTO = this.getRoleDegreeResource(new RoleResourceDTO(), 1L, roleResourceDTOS);
        userLoginDTO.setRoleResourceDTOS(roleResourceDTO.getRoleResourceDTOS());

        //打印token 方便调试
        log.info("登陆成功，token------>[{}]", session.getId());
        response.getWriter()
                .write(objectMapper.writeValueAsString(Result.success(ExceptionEnum.LOGIN_SUCCESS, userLoginDTO)));
    }


    private RoleResourceDTO getRoleDegreeResource(RoleResourceDTO roleResourceDTO, Long parentId, List<RoleResourceDTO> list) {
        List<RoleResourceDTO> collect = list.stream().filter(a -> a.getParentId().equals(parentId)).collect(Collectors.toList());
        collect = collect.stream().sorted(Comparator.comparing(RoleResourceDTO::getSeq)).collect(Collectors.toList());
        roleResourceDTO.setRoleResourceDTOS(collect);
        list.removeAll(collect);
        if (list.size() > 0) {
            for (RoleResourceDTO dto : collect) {
                getRoleDegreeResource(dto, dto.getId(), list);
            }
        }
        return roleResourceDTO;
    }
}
