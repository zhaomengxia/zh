package com.zh.mapper.sys;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.dto.sys.user.SysUserQueryDTO;
import com.zh.dto.sys.user.SysUserShowDTO;
import com.zh.entity.sys.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author  赵梦霞
 * @since 2018-12-18
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * @author  赵梦霞
     * @since 2018/12/18 11:27
     * @param username 用户名
     * @Description 根据用户名查找用户
     **/
    SysUser findByUserName(@Param("username") String username);

    /**
     * @author  赵梦霞
     * @since 2018/12/18 11:27
     * @param mobile 用户手机号
     * @Description 根据用户手机号查找用户
     **/
    SysUser findByUserMobile(@Param("mobile") String mobile);

    /**
     * @author  赵梦霞
     * @since 2018/12/19 14:08
     * @param page 分页对象
     * @param queryDTO 查询条件
     **/
    IPage<SysUserShowDTO> selectShowPage(Page<SysUserShowDTO> page, @Param("queryDTO") SysUserQueryDTO queryDTO);

    /**
     * @author  赵梦霞
     * @since 2018/12/20 15:08
     * @param id 主键
     * @Description 查询用户信息
     **/
    SysUserShowDTO selectUserInfo(@Param("id") Long id);

}
