package com.zh.service.sys;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zh.dto.sys.user.ChangePasswordDTO;
import com.zh.dto.sys.user.SysUserInertOrUpdateDTO;
import com.zh.dto.sys.user.SysUserQueryDTO;
import com.zh.dto.sys.user.SysUserShowDTO;
import com.zh.entity.sys.SysUser;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author  赵梦霞
 * @since 2018-12-18
 */
public interface SysUserService extends IService<SysUser> {
    List<SysUser> checkUserName(String name);

    boolean checkUserExist(SysUserInertOrUpdateDTO userInertOrUpdateDTO);

    boolean saveOrUpdate(SysUserInertOrUpdateDTO userInertOrUpdateDTO);

    IPage<SysUserShowDTO> queryPage(Page<SysUserShowDTO> page, SysUserQueryDTO queryDTO);

    SysUserShowDTO queryUserInfo(Long id);

    boolean changePassword(ChangePasswordDTO changePasswordDTO);

    boolean resetPassword(Long id);
}
