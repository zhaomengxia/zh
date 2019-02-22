package com.zh.service.test2.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.zh.dto.user.ChangePasswordDTO;
import com.zh.dto.user.SysUserInertOrUpdateDTO;
import com.zh.dto.user.SysUserQueryDTO;
import com.zh.dto.user.SysUserShowDTO;
import com.zh.entity.test2.*;
import com.zh.enums.ExceptionEnum;
import com.zh.mapper.test2.ZUserMapper;
import com.zh.security.userdetails.UserDetailsServiceExpansion;
import com.zh.service.test2.ZResourcesService;
import com.zh.service.test2.ZRolesResourcesService;
import com.zh.service.test2.ZUserRolesService;
import com.zh.service.test2.ZUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author  hahaha 
 * @since 2019-02-21
 */
@Service
public class ZUserServiceImpl extends ServiceImpl<ZUserMapper, ZUser> implements ZUserService,UserDetailsServiceExpansion {

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private ZUserMapper zUserMapper;
    @Resource
    private ZUserRolesService zUserRolesService;
    @Resource
    private ZRolesResourcesService zRolesResourcesService;
    @Resource
    private ZResourcesService zResourcesService;

    //默认用户密码
    private String defaultPassword = "123456";


    @Override
    public List<ZUser> checkUserName(String name) {
        return null;
    }

    @Override
    public boolean checkUserExist(SysUserInertOrUpdateDTO userInertOrUpdateDTO) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(SysUserInertOrUpdateDTO userInertOrUpdateDTO) {
        //添加用户表
        ZUser zUser=new ZUser();
        zUser.setName("meng");
        zUser.setPassword(passwordEncoder.encode(defaultPassword));
        this.saveOrUpdate(zUser);
        //永固角色关联
        ZUserRoles zUserRoles=new ZUserRoles();
        zUserRoles.setSysRolesId(1L);
        zUserRoles.setSysUserId(zUser.getId());
        zUserRolesService.saveOrUpdate(zUserRoles);
        return true;
    }

    @Override
    public IPage<SysUserShowDTO> queryPage(Page<SysUserShowDTO> page, SysUserQueryDTO queryDTO) {
        return null;
    }

    @Override
    public SysUserShowDTO queryUserInfo(Long id) {
        return null;
    }

    @Override
    public boolean changePassword(ChangePasswordDTO changePasswordDTO) {
        return false;
    }

    @Override
    public boolean resetPassword(Long id) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsernameMobile(String mobile) throws UsernameNotFoundException {
        ZUser zUser=zUserMapper.findByMobile(mobile);
        if (zUser==null){
            throw new BadCredentialsException(ExceptionEnum.user_not_exist.getMessage());
        }
        return permissionFilter(zUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ZUser zUser= zUserMapper.findByUserName(username);
        if (zUser==null){
            throw new BadCredentialsException(ExceptionEnum.user_not_exist.getMessage());
        }
        return permissionFilter(zUser);
    }
    public UserDetails permissionFilter(ZUser zUser){
            List<ZRoles> zRoles=zUser.getRoles();
            for (ZRoles zRoles1:zRoles) {
                List<ZResources> zResources=new ArrayList<>();
               for (ZResources zResources1:zRoles1.getResources()){
                   if (zResources1.getHasPersission()) {
                       zResources.add(zResources1);
                   }
               }
               zRoles1.setResources(zResources);
            }
            return zUser;
    }
}
