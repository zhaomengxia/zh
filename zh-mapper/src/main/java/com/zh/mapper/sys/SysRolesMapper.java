package com.zh.mapper.sys;

import com.zh.dto.sys.role.SysRoleDTO;
import com.zh.entity.sys.SysRoles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author  赵梦霞
 * @since 2018-12-18
 */
public interface SysRolesMapper extends BaseMapper<SysRoles> {
    List<SysRoleDTO> selectAll();

    SysRoleDTO checkNameIsSame(@Param("name") String name);

    List<SysRoleDTO> selectByIdAsParentId(@Param("id") Long id);
}
