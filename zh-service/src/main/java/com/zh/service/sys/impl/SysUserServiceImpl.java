package com.zh.service.sys.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.zh.dto.sys.user.ChangePasswordDTO;
import com.zh.dto.sys.user.SysUserInertOrUpdateDTO;
import com.zh.dto.sys.user.SysUserQueryDTO;
import com.zh.dto.sys.user.SysUserShowDTO;
import com.zh.entity.sys.SysResources;
import com.zh.entity.sys.SysRoles;
import com.zh.entity.sys.SysUser;
import com.zh.entity.sys.SysUserRoles;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.mapper.sys.SysUserMapper;
import com.zh.security.userdetails.UserDetailsServiceExpansion;
import com.zh.service.sys.SysUserRolesService;
import com.zh.service.sys.SysUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author  赵梦霞
 * @since 2018-12-18
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService, UserDetailsServiceExpansion {

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysUserRolesService sysUserRolesService;
    /*@Resource
    private SysUserOrganizationService sysUserOrganizationService;*/

    //默认用户密码
    private String defaultPassword = "123456";

    @Override
    public UserDetails loadUserByUsernameMobile(String mobile) throws UsernameNotFoundException {
        SysUser sysUser = sysUserMapper.findByUserMobile(mobile);
        if (sysUser == null) {
            throw new BadCredentialsException(ExceptionEnum.user_not_exist.getMessage());
        }
        //过滤权限
        return permissionFilter(sysUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = sysUserMapper.findByUserName(username);
        if (sysUser == null) {
            throw new BadCredentialsException(ExceptionEnum.user_not_exist.getMessage());
        }
        //过滤权限
        return permissionFilter(sysUser);
    }

    private UserDetails permissionFilter(SysUser sysUser) {
        for (SysRoles role : sysUser.getRoles()) {
            List<SysResources> sysResources = new ArrayList<>();
            for (SysResources resource : role.getResources()) {
                if (resource.getHasPersission()) {
                    sysResources.add(resource);
                }
            }
            role.setResources(sysResources);
        }
        return sysUser;
    }

    @Override
    public List<SysUser> checkUserName(String name) {
        if (StrUtil.isBlank(name)) {
            throw new UnifiedException("请填负责人！");
        }
        List<SysUser> sysUsers = this.list(new QueryWrapper<SysUser>().lambda().eq(SysUser::getName, name));
        if (sysUsers.size() == 0) {
            throw new UnifiedException("此人不是系统用户，请先到“系统管理-用户管理”中添加！");
        }
        return sysUsers;
    }

    @Override
    public boolean checkUserExist(SysUserInertOrUpdateDTO userInertOrUpdateDTO) {
        //如果为修改用户信息 用户名可以不做更改
        if (userInertOrUpdateDTO.getId() != null) {
            SysUser sysUser = sysUserMapper.selectById(userInertOrUpdateDTO.getId());
            if (!sysUser.getName().equals(userInertOrUpdateDTO.getName())) {
                return this.count(new QueryWrapper<SysUser>().lambda().eq(SysUser::getName, userInertOrUpdateDTO.getName())) == 0;
            }
            return true;
        } else {
            return this.count(new QueryWrapper<SysUser>().lambda().eq(SysUser::getName, userInertOrUpdateDTO.getName())) == 0;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdate(SysUserInertOrUpdateDTO userInertOrUpdateDTO) {
        //检查是否存在相同用户名用户
        if (!this.checkUserExist(userInertOrUpdateDTO)) {
            throw new UnifiedException(ExceptionEnum.USER_ALREADY_EXIST);
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userInertOrUpdateDTO, sysUser);
        //新增用户 分配一个默认密码
        if (userInertOrUpdateDTO.getId() == null) {
            sysUser.setPassword(passwordEncoder.encode(defaultPassword));
        }
        List<SysUserRoles> sysUserRoles = Lists.newArrayList();
        /*List<SysUserOrganization> userOrganizations = Lists.newArrayList();*/
        if (this.saveOrUpdate(sysUser)) {
            //删除用户-角色关联关系
            sysUserRolesService.remove(new LambdaQueryWrapper<SysUserRoles>().eq(SysUserRoles::getSysUserId, sysUser.getId()));
            //删除用户-部门关联关系
//            sysUserOrganizationService.remove(new LambdaQueryWrapper<SysUserOrganization>().eq(SysUserOrganization::getSysUserId, sysUser.getId()));
            //组装用户-角色关联关系
            Arrays.stream(StrUtil.splitToLong(userInertOrUpdateDTO.getRoles(), StrUtil.COMMA)).forEach(roleId ->
                    sysUserRoles.add(SysUserRoles.builder()
                            .sysUserId(sysUser.getId())
                            .sysRolesId(roleId)
                            .build())
            );
            //组装用户-部门关联关系
//            sysUserOrganizations.forEach(organization ->
//                    userOrganizations.add(SysUserOrganization.builder()
//                            .organizationId(organization.getOrganizationId())
//                            .sysUserId(sysUser.getId())
//                            .position(organization.getPosition())
//                            .build())
//            );
        }
        //保存用户-角色关联关系
        return sysUserRolesService.saveBatch(sysUserRoles);
        //return sysUserOrganizationService.saveBatch(userOrganizations);
    }

    @Override
    public IPage<SysUserShowDTO> queryPage(Page<SysUserShowDTO> page, SysUserQueryDTO queryDTO) {
        return sysUserMapper.selectShowPage(page, queryDTO);
    }

    @Override
    public SysUserShowDTO queryUserInfo(Long id) {
        return sysUserMapper.selectUserInfo(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean changePassword(ChangePasswordDTO changePasswordDTO) {
        //密码是否正确
        SysUser sysUser = sysUserMapper.selectById(changePasswordDTO.getId());
        if (!passwordEncoder.matches(changePasswordDTO.getOldPass(), sysUser.getPassword())) {
            throw new UnifiedException(ExceptionEnum.OLD_PASSWORD_NOT_MATCH);
        }
        //设置新密码
        sysUser.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPass()));
        return sysUserMapper.updateById(sysUser) == 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean resetPassword(Long id) {
        SysUser sysUser = sysUserMapper.selectById(id);
        if (sysUser != null) {
            sysUser.setPassword(passwordEncoder.encode(defaultPassword));
            return sysUserMapper.updateById(sysUser) == 1;
        } else {
            return false;
        }
    }
}
