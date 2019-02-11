package com.zh.service.sys;

import com.zh.dto.sys.role.SysRoleDTO;
import com.zh.entity.sys.SysRoles;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author  赵梦霞
 * @since 2018-12-18
 */
public interface SysRolesService extends IService<SysRoles> {

    /**
     * @author  赵梦霞
     * @since 2018/12/20 11:41
     * @param roleId 角色表主键
     * @Description 检查角色是否被使用
     **/
    boolean checkRolesBind(Long roleId);

    List<SysRoleDTO> selectTree();

    boolean checkNameIsSame(SysRoles sysRoles);

    boolean selectByIdAsParentId(Long id);

}
