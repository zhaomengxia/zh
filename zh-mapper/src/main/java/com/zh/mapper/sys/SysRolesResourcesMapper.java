package com.zh.mapper.sys;

import com.zh.dto.sys.role.SysRoleResourseDTO;
import com.zh.entity.sys.SysRolesResources;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 角色-资源 关联表 Mapper 接口
 * </p>
 *
 * @author  赵梦霞
 * @since 2018-12-18
 */
public interface SysRolesResourcesMapper extends BaseMapper<SysRolesResources> {
    Boolean deleteByRoleId(@Param("roleId") Long roleId);

    List<SysRoleResourseDTO> selectByRoleId(@Param("roleId") Long roleId);

}
