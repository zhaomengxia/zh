package com.zh.security.sms.filter;

import cn.hutool.core.util.StrUtil;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.security.exception.ValidateCodeException;
import com.zh.security.sms.code.Code;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码过滤器
 *
 * @author  赵梦霞
 * @since 2018-12-12 16:47

 **/
@Data
@EqualsAndHashCode(callSuper = false)
public class VerificationFilter extends OncePerRequestFilter {

    private RedisTemplate<String, Code> redisTemplate;

    private AntPathRequestMatcher antPathMatcher = new AntPathRequestMatcher("/authentication/sms/login", HttpMethod.POST.toString());

    private AuthenticationFailureHandler authenticationFailureHandler;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (antPathMatcher.matches(request)) {
            try {
                validate(request);
            } catch (ValidateCodeException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }

        filterChain.doFilter(request, response);

    }

    private void validate(HttpServletRequest request) throws ServletRequestBindingException {
        //获取手机号
        String mobile = ServletRequestUtils.getStringParameter(request, "mobile");
        if (StrUtil.isBlank(mobile)) {
            throw new UnifiedException(ExceptionEnum.MOBILE_IS_BLANK);
        }
        //从redis中获取Code对象
        Code codeInSession = redisTemplate.opsForValue().get(mobile);
        //获取request中code值
        String codeInRequest = ServletRequestUtils.getStringParameter(request, "code");

        if (StrUtil.isBlank(codeInRequest)) {
            throw new ValidateCodeException(ExceptionEnum.CODE_VALUE_IS_BLANK.getMessage());
        }

        if (codeInSession == null) {
            throw new ValidateCodeException(ExceptionEnum.code_not_exist.getMessage());
        }

        if (codeInSession.isExpried()) {
            throw new ValidateCodeException(ExceptionEnum.CODE_VALUE_IS_EXPRIED.getMessage());
        }

        if (!StrUtil.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException(ExceptionEnum.CODE_VALUE_NOT_MATCH.getMessage());
        }

        //移除session中验证码信息
        redisTemplate.delete(mobile);
    }


}
