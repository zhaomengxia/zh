package com.zh.controller.sys;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.entity.sys.SysRolesResources;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.sys.SysRolesResourcesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 角色-资源 关联表 前端控制器
 * </p>
 *
 * @author  赵梦霞
 * @since 2018-12-18
 */
@RestController
@RequestMapping("/sysRolesResources")
@Api(description = "给角色分配资源")
public class SysRolesResourcesController {
    @Resource
    private SysRolesResourcesService sysRolesResourcesService;

    /**
     * 给角色分配资源
     *
     * @param sysRolesResourcesList
     * @return
     */
    @ApiOperation(value = "给角色分配资源")
    @PostMapping(value = "/addOrUpdate")
    @Log(desc = "给角色分配资源")
    @Transactional(rollbackFor = Exception.class)
    public Result addOrUpdate(@RequestBody @Valid List<SysRolesResources> sysRolesResourcesList) {
        boolean remove = sysRolesResourcesService.remove(new LambdaQueryWrapper<SysRolesResources>().
                eq(SysRolesResources::getSysRolesId, sysRolesResourcesList.get(0).getSysRolesId()));
        //该角色已分配资源  且 已经删除 则执行本次分配资源操作
        if (remove) {
            if (sysRolesResourcesService.saveBatch(sysRolesResourcesList)) {
                return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
            }
        }

        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);

    }

    @ApiOperation(value = "获得角色资源")
    @GetMapping(value = "/selectAllByRoleId")
    @Log(desc = "获得角色资源")
    public Result selectAllByRoleId(Long roleId) {
        return Result.success(sysRolesResourcesService.selectAllByRoleId(roleId));
    }

}

