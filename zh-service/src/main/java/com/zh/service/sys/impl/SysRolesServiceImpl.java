package com.zh.service.sys.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.dto.sys.role.SysRoleDTO;
import com.zh.entity.sys.SysRoles;
import com.zh.entity.sys.SysUserRoles;
import com.zh.mapper.sys.SysRolesMapper;
import com.zh.service.sys.SysRolesService;
import com.zh.service.sys.SysUserRolesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author  赵梦霞
 * @since 2018-12-18
 */
@Service
public class SysRolesServiceImpl extends ServiceImpl<SysRolesMapper, SysRoles> implements SysRolesService {

    @Resource
    private SysUserRolesService sysUserRolesService;

    @Resource
    private SysRolesMapper sysRolesMapper;

    @Override
    public boolean checkRolesBind(Long roleId) {
        List<SysUserRoles> list = sysUserRolesService.list(new LambdaQueryWrapper<SysUserRoles>().eq(SysUserRoles::getSysRolesId, roleId));
        return CollUtil.isEmpty(list);
    }

    @Override
    public List<SysRoleDTO> selectTree() {
        List<SysRoleDTO> sysRoleDTOS=sysRolesMapper.selectAll();
        return this.getChildRole(sysRoleDTOS,-1L);
    }

    @Override
    public boolean checkNameIsSame(SysRoles sysRoles) {
        boolean f=false;
        SysRoleDTO sysRoleDTO= sysRolesMapper.checkNameIsSame(sysRoles.getRoleName());
        if (sysRoleDTO!=null){
            if (sysRoles.getId()!=null&&sysRoles.getId().equals(sysRoleDTO.getId())){
                f=true;
            }
        }
        else{
            f=true;
        }
        return f;
    }

    @Override
    public boolean selectByIdAsParentId(Long id) {

        boolean f=false;
        List<SysRoleDTO> sysRoleDTOS=sysRolesMapper.selectByIdAsParentId(id);
        if (null!=sysRoleDTOS&&sysRoleDTOS.size()>0){
            f=false;
        }
        else{
            f=true;
        }
        return f;
    }


    private List<SysRoleDTO> getChildRole(List<SysRoleDTO> sysRoleDTOS, long id) {
        List<SysRoleDTO> sysRoleDTOList = new ArrayList<>();
        for (SysRoleDTO sysRoleDTO : sysRoleDTOS) {
            //获得父节点为id下的角色的信息
            if (sysRoleDTO.getParentId() == id) {
                SysRoleDTO sysRoleDTO1 = sysRoleDTO;
                //递归，将此时的id和basicAdministrativeDivisionDTOS再传进getChildRole（List<BasicAdministrativeDivisionDTO>
                // basicAdministrativeDivisionDTOS, long id）
                // 执行该方法
                List<SysRoleDTO> sysRoleDTOList1 =
                        this.getChildRole(sysRoleDTOS, sysRoleDTO1.getId());
                sysRoleDTO1.setChildren(sysRoleDTOList1);
                sysRoleDTOList.add(sysRoleDTO1);
            }
        }
        return sysRoleDTOList;
    }
}
