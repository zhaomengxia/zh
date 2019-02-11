package com.zh.service.sys;

import com.zh.dto.sys.role.SysRoleResourseDTO;
import com.zh.entity.sys.SysRolesResources;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色-资源 关联表 服务类
 * </p>
 *
 * @author  赵梦霞
 * @since 2018-12-18
 */
public interface SysRolesResourcesService extends IService<SysRolesResources> {
    /**
     * 根据角色id 查找 该角色所有被分配的资源
     * @param roleId
     * @return
     */
    List<SysRoleResourseDTO> selectAllByRoleId(Long roleId);

    List<SysRolesResources> saveAllRoleAndResourses(Long roleId);
}
