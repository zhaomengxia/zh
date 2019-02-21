package com.zh.service.test2.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.zh.dto.user.ChangePasswordDTO;
import com.zh.dto.user.SysUserInertOrUpdateDTO;
import com.zh.dto.user.SysUserQueryDTO;
import com.zh.dto.user.SysUserShowDTO;
import com.zh.entity.test2.ZResources;
import com.zh.entity.test2.ZRoles;
import com.zh.entity.test2.ZRolesResources;
import com.zh.entity.test2.ZUser;
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
        return false;
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
        List<ZUser> zUsers= this.list(new LambdaQueryWrapper<ZUser>().eq(ZUser::getMobile,mobile));
        if (zUsers==null){
            throw new BadCredentialsException(ExceptionEnum.user_not_exist.getMessage());
        }
        return permissionFilter(zUsers.get(0));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        List<ZUser> zUsers= this.list(new LambdaQueryWrapper<ZUser>().eq(ZUser::getUsername,s));
        if (zUsers==null){
            throw new BadCredentialsException(ExceptionEnum.user_not_exist.getMessage());
        }
        return permissionFilter(zUsers.get(0));
    }
    public UserDetails permissionFilter(ZUser zUser){
            List<ZRoles> zRoles=zUser.getRoles();
            for (ZRoles zRoles1:zRoles) {
                List<ZRolesResources> zRolesResources = zRolesResourcesService.list(new LambdaQueryWrapper<ZRolesResources>()
                        .eq(ZRolesResources::getSysRolesId, zRoles1.getId()));
                for (ZRolesResources zRolesResources1:zRolesResources){
                    if (zRolesResources1.getHasPersission()){
                        List<ZResources> zResources=zResourcesService.list(new LambdaQueryWrapper<ZResources>().eq(ZResources::getId,zRolesResources1.getSysResourcesId()));
                        zRoles1.setResources(zResources);
                    }
                }
            }
            return zUser;
    }
}
