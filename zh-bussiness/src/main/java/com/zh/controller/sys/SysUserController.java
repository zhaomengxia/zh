package com.zh.controller.sys;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.dto.sys.user.ChangePasswordDTO;
import com.zh.dto.sys.user.SysUserInertOrUpdateDTO;
import com.zh.dto.sys.user.SysUserQueryDTO;
import com.zh.dto.sys.user.SysUserShowDTO;
import com.zh.entity.sys.SysRoles;
import com.zh.entity.sys.SysUserRoles;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.sys.SysRolesService;
import com.zh.service.sys.SysUserRolesService;
import com.zh.service.sys.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author  赵梦霞
 * @since 2018-12-18
 */
@RestController
@RequestMapping("/sysUser")
@Api(description = "用户管理")
@Validated
public class SysUserController {

    @Resource
    private SysUserService sysUserService;
    @Resource
    private SysUserRolesService sysUserRolesService;
    @Resource
    private SysRolesService sysRolesService;

    @GetMapping(value = "/getAllRoleByType")
    @ApiOperation(value = "获得角色列表")
    public Result getAllRoleByType(){
        return Result.success(sysRolesService.list(new LambdaQueryWrapper<SysRoles>().eq(SysRoles::getRoleType,1).select(SysRoles::getId,SysRoles::getRoleName,SysRoles::getRoleType)));
    }

    @ApiOperation(value = "查询用户列表（分页）")
    @GetMapping("/page")
    public Result page(Page<SysUserShowDTO> page, SysUserQueryDTO queryDTO) {
        return Result.success(sysUserService.queryPage(page, queryDTO));
    }

    @ApiOperation(value = "根据用户主键查询用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户主键")
    })
    @GetMapping("/{id}")
    public Result sysUser(@PathVariable("id") Long id) {
        return Result.success(sysUserService.queryUserInfo(id));
    }

    @PostMapping("/saveOrUpdate")
    @ApiOperation(value = "新增或者修改用户")
    @Log(desc = "新增或者修改用户")
    public Result add(@RequestBody @Valid SysUserInertOrUpdateDTO userInertOrUpdateDTO) {
        if (sysUserService.saveOrUpdate(userInertOrUpdateDTO)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }

    @ApiOperation(value = "修改密码")
    @PostMapping("/password")
    @Log(desc = "修改密码")
    public Result modifyPassword(@RequestBody @Valid ChangePasswordDTO changePasswordDTO) {
        if (sysUserService.changePassword(changePasswordDTO)) {
            return Result.success(ExceptionEnum.CHANGE_PASSWORD_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.CHANGE_PASSWORD_FAIL);
    }

    @ApiOperation(value = "重置用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户主键", required = true)
    })
    @PostMapping("/reset/{id}")
    @Log(desc = "重置用户密码")
    public Result resetPassword(@PathVariable Long id) {
        if (sysUserService.resetPassword(id)) {
            return Result.success(ExceptionEnum.RESET_PASSWORD_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.RESET_PASSWORD_FAIL);
    }


    @ApiOperation(value = "删除用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户主键")
    })
    @DeleteMapping("/delete/{id}")
    @Log(desc = "删除用户")
    @Transactional(rollbackFor = Exception.class)
    public Result delete(@PathVariable("id") Long id) {
        //同时删除用户 与用户-角色关联关系
        if (sysUserService.removeById(id) && sysUserRolesService.remove(new LambdaQueryWrapper<SysUserRoles>().eq(SysUserRoles::getSysUserId, id))) {
            return Result.success(ExceptionEnum.DELETE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
    }

    @ApiOperation(value = "批量刪除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "主键字符串用 ',' 分割 ")
    })
    @DeleteMapping("/deleteBatch/{ids}")
    @Log(desc = "批量删除用户")
    @Transactional(rollbackFor = Exception.class)
    public Result deleteBatch(@PathVariable("ids") String ids) {
        if (StrUtil.isNotBlank(ids)) {
            List<Long> idList = Arrays.stream(StrUtil.splitToLong(ids, StrUtil.COMMA)).boxed().collect(Collectors.toList());
            //同时删除用户 与用户-角色关联关系
            if (sysUserService.removeByIds(idList) && sysUserRolesService.remove(new LambdaQueryWrapper<SysUserRoles>().in(SysUserRoles::getSysUserId, idList))) {
                return Result.success(ExceptionEnum.DELETE_SUCCESS);
            }
            throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
        }
        throw new UnifiedException(ExceptionEnum.LACK_PARAMETER);
    }

}

