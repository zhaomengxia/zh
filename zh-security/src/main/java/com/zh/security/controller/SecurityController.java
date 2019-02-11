package com.zh.security.controller;

import com.zh.api.Result;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import org.springframework.http.HttpStatus;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author  赵梦霞
 * @since 2018-11-05 16:20

 **/
@RestController
public class SecurityController {

    @Resource
    private FindByIndexNameSessionRepository repository;

    /**
     * @param token
     * @author  赵梦霞
     * @Description 检查token是否过期
     * @since 2018/12/14 15:23
     **/
    @PostMapping("/authentication/check")
    public Result check(String token) {
        Session session = repository.findById(token);
        if (session == null || session.isExpired()) {
            throw new UnifiedException(ExceptionEnum.TOKEN_INVALID);
        }
        return Result.success(ExceptionEnum.TOKEN_ALLOW);
    }

    /**
     * @author  赵梦霞
     * @Description 替换默认登录页，返回json由前端引导至登陆页面
     * @since 2018/12/14 15:22
     **/
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @GetMapping("/authentication/require")
    public Result require() {
        throw new UnifiedException(ExceptionEnum.NEED_LOGIN);
    }

}
