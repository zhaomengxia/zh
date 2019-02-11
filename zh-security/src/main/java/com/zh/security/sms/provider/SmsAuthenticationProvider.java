package com.zh.security.sms.provider;

import com.zh.security.sms.authentication.SmsAuthenticationToken;
import com.zh.security.userdetails.UserDetailsServiceExpansion;
import lombok.Data;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 手机验证码 校验处理器
 *
 * @author  赵梦霞
 * @since 2018-12-12 11:39

 **/
@Data
public class SmsAuthenticationProvider implements AuthenticationProvider {

    private UserDetailsServiceExpansion userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsAuthenticationToken authenticationToken = (SmsAuthenticationToken) authentication;
        UserDetails user = userDetailsService.loadUserByUsernameMobile((String) authenticationToken.getPrincipal());
        SmsAuthenticationToken authenticationResult = new SmsAuthenticationToken(user,user.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SmsAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
