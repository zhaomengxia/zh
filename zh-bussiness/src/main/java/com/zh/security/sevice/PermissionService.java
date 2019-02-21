package com.zh.security.sevice;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Sets;

import com.zh.entity.test2.ZResources;
import com.zh.entity.test2.ZRoles;
import com.zh.entity.test2.ZUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 权限校验
 **/
@Component("permissionService")
public class PermissionService implements Permission {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        if (principal instanceof UserDetails) {
            try {
                ZUser sysUser = (ZUser) principal;
                //获取用户所有角色
                List<ZRoles> roles = sysUser.getRoles();

                //系统管理员拥有所有权限
                if (sysUser.getAuthorities().parallelStream().anyMatch(e -> "系统管理员".equals(e.getAuthority()) || "炒鸡管理员".equals(e.getAuthority()))) {
                    return true;
                }

                //读取用户所拥有的所有权限
                Set<String> urls = Sets.newHashSet();
                for (ZRoles role : roles) {
                    urls.addAll(role.getResources().parallelStream().map(ZResources::getHttpPath).collect(Collectors.toSet()));
                }

                //判断接口是否有关联关系
                urls = CollUtil.newHashSet(StrUtil.split(urls.parallelStream().filter(StrUtil::isNotBlank).map(e -> {
                    if (e.contains("|")) {
                        String[] split = StrUtil.split(e, "|");
                        return String.join(StrUtil.COMMA, split);
                    }
                    return e;
                }).collect(Collectors.joining(StrUtil.COMMA)), StrUtil.COMMA));

                //过滤用户权限
                for (String url : urls) {
                    if (StrUtil.isEmpty(url)) {
                        continue;
                    }
                    //去除项目配置访问根路径后进行匹配
                    if (antPathMatcher.match(url, StrUtil.replace(request.getRequestURI(), contextPath, StrUtil.EMPTY))) {
                        hasPermission = true;
                        break;
                    }
                }
            } catch (ClassCastException e) {
                return false;
            }
        }
        return hasPermission;
    }
}
