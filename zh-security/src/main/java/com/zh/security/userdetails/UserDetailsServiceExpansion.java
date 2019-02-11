package com.zh.security.userdetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 扩展 spring UserDetailService 接口
 *
 * @author  hahaha
 * @date 2018-12-12 11:52

 **/
public interface UserDetailsServiceExpansion extends UserDetailsService  {

    /**
     * @author  hahaha
     * @since 2018/12/12 11:53
     * @param mobile 用户手机号码
     * @Description 根据用户手机号码查询用户信息
     **/
    UserDetails loadUserByUsernameMobile(String mobile) throws UsernameNotFoundException;

}
