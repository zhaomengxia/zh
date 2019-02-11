package com.zh.service.sys.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zh.dto.sys.role.SysRoleResourseDTO;
import com.zh.entity.sys.SysResources;
import com.zh.entity.sys.SysRolesResources;
import com.zh.mapper.sys.SysRolesResourcesMapper;
import com.zh.service.sys.SysResourcesService;
import com.zh.service.sys.SysRolesResourcesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 角色-资源 关联表 服务实现类
 * </p>
 *
 * @author  赵梦霞
 * @since 2018-12-18
 */
@Service
public class SysRolesResourcesServiceImpl extends
        ServiceImpl<SysRolesResourcesMapper, SysRolesResources> implements SysRolesResourcesService {
    @Resource
    private SysRolesResourcesMapper sysRolesResourcesMapper;
    @Resource
    private SysResourcesService sysResourcesService;

    @Override
    public List<SysRoleResourseDTO> selectAllByRoleId(Long roleId) {
        List<SysRoleResourseDTO> sysRolesResources = sysRolesResourcesMapper.selectByRoleId(roleId);
        return this.getChildRole(sysRolesResources, -1L);
    }

    @Override
    public List<SysRolesResources> saveAllRoleAndResourses(Long roleId) {
        List<SysRolesResources> sysRolesResources = new ArrayList<>();
        List<SysResources> sysResources = sysResourcesService.list(new QueryWrapper<SysResources>().select("id"));
        for (SysResources sysResources1 : sysResources) {
            SysRolesResources sysRolesResources1 = new SysRolesResources();
            sysRolesResources1.setHasPersission(false);
            sysRolesResources1.setSysResourcesId(sysResources1.getId());
            sysRolesResources1.setSysRolesId(roleId);
            sysRolesResources.add(sysRolesResources1);
        }
        return sysRolesResources;
    }

    private List<SysRoleResourseDTO> getChildRole(List<SysRoleResourseDTO>
                                                          sysRoleResourseDTOS, long id) {
        List<SysRoleResourseDTO> sysRoleResourseDTOList = new ArrayList<>();
        for (SysRoleResourseDTO sysRoleResourseDTO : sysRoleResourseDTOS) {
            //获得父节点为id下的角色的信息
            if (sysRoleResourseDTO.getParentId() == id) {
                SysRoleResourseDTO sysRoleResourseDTO1 = sysRoleResourseDTO;
                //递归，将此时的id和basicAdministrativeDivisionDTOS再传进getChildRole（List<BasicAdministrativeDivisionDTO>
                // basicAdministrativeDivisionDTOS, long id）
                // 执行该方法
                List<SysRoleResourseDTO> basicAdministrativeDivisionDTOS1 =
                        this.getChildRole(sysRoleResourseDTOS, sysRoleResourseDTO1.getId());
                sysRoleResourseDTO1.setChildren(basicAdministrativeDivisionDTOS1);
                sysRoleResourseDTOList.add(sysRoleResourseDTO1);
            }
        }
        return sysRoleResourseDTOList;
    }
}
