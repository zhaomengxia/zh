package com.zh.security.userdetails;

import lombok.Data;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

/**
 * 默认UserDetailsService
 *
 * @author  hahaha
 * @since 2018-12-14 13:56

 **/
@Data
public class DefaultUserDetailsService implements UserDetailsServiceExpansion {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsernameMobile(String mobile) throws UsernameNotFoundException {
        return new User("admin", passwordEncoder.encode("1234"), AuthorityUtils.createAuthorityList("admin"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(username, passwordEncoder.encode(username), AuthorityUtils.createAuthorityList("admin"));
    }
}
