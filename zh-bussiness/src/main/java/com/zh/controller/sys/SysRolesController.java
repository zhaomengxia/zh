package com.zh.controller.sys;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.entity.sys.SysRoles;
import com.zh.entity.sys.SysRolesResources;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.sys.SysResourcesService;
import com.zh.service.sys.SysRolesResourcesService;
import com.zh.service.sys.SysRolesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author  赵梦霞
 * @since 2018-12-18
 */
@RestController
@RequestMapping("/sysRoles")
@Api(description = "角色管理")
public class SysRolesController {

    @Resource
    private SysRolesService sysRolesService;

    @Resource
    private SysResourcesService sysResourcesService;
    @Resource
    private SysRolesResourcesService sysRolesResourcesService;

    @ApiOperation(value = "角色列表")
    @GetMapping("/list")
    @Log(desc = "角色列表")
    public Result list() {
        return Result.success(sysRolesService.selectTree());
    }

    @ApiOperation(value = "角色列表，无树形结构,用户管理角色搜索框")
    @GetMapping("/simpleList")
    @Log(desc = "角色列表，无树形结构,用户管理角色搜索框")
    public Result simpleList() {
        return Result.success(sysRolesService
                .list(new LambdaQueryWrapper<SysRoles>().select(SysRoles::getId, SysRoles::getRoleName).eq(SysRoles::getRoleType, 1)));
    }

    @PostMapping("/saveOrUpdate")
    @ApiOperation(value = "新增/修改角色信息")
    @Log(desc = "新增/修改角色信息")
    @Transactional(rollbackFor = Exception.class)
    public Result saveOrUpdate(@RequestBody @Valid SysRoles sysRoles) {
        if (null != sysRoles.getId() && !sysRolesService.checkRolesBind(sysRoles.getId())) {
            throw new UnifiedException("该角色已被用不能被修改!");
        }
        if (sysRolesService.checkNameIsSame(sysRoles)) {
            if (sysRolesService.saveOrUpdate(sysRoles)) {
                //先将  该角色资源给清除
                boolean remove = sysRolesResourcesService.remove(new LambdaQueryWrapper<SysRolesResources>().
                        eq(SysRolesResources::getSysRolesId, sysRoles.getId()));
                if (remove) {
                    //清除后 再更新
                    List<SysRolesResources> sysRolesResources = sysRolesResourcesService.saveAllRoleAndResourses(sysRoles.getId());
                    if (sysRolesResourcesService.saveBatch(sysRolesResources)) {
                        return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
                    }
                }
            }
            throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
        } else {
            return Result.fail("该角色名称已存在！");
        }
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色主键")
    })
    @Log(desc = "删除角色信息")
    @Transactional(rollbackFor = Exception.class)
    public Result delete(@PathVariable("id") Long id) {
        //这里首先要判断前端传过来需要删除的角色或角色组 是否还有下级 及是否被用户绑定  如果有则不能删除
        if (!sysRolesService.selectByIdAsParentId(id)) {
            throw new UnifiedException("先删除下级!");
        }
        if (!sysRolesService.checkRolesBind(id)) {
            throw new UnifiedException("该角色已被用!");
        }
        if (sysRolesService.removeById(id)) {
            return Result.success(ExceptionEnum.DELETE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
    }

}

