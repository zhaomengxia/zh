package com.zh.security.strategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zh.api.Result;
import com.zh.enums.ExceptionEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 默认session过期策略
 *
 * @author  赵梦霞
 * @since 2018-12-14 15:14

 **/
public class DefaultInvalidSessionStrategy implements InvalidSessionStrategy {

    @Resource
    private ObjectMapper objectMapper;

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(objectMapper.writeValueAsString(Result.fail(ExceptionEnum.TOKEN_INVALID)));
    }
}
