package com.zh.controller.basic;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.dto.basic.BasicDivisionOrganizationDTO;
import com.zh.dto.basic.BasicDivisionOrganizationPeopleDTO;
import com.zh.dto.basic.UserDepartmentDTO;
import com.zh.entity.basic.BasicAdministrativeDivision;
import com.zh.entity.basic.BasicCommandDepartment;
import com.zh.entity.basic.BasicDivisionOrganization;
import com.zh.entity.basic.UserDepartment;
import com.zh.entity.sys.SysUser;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.basic.BasicAdministrativeDivisionService;
import com.zh.service.basic.BasicCommandDepartmentService;
import com.zh.service.basic.BasicDivisionOrganizationService;
import com.zh.service.basic.UserDepartmentService;
import com.zh.service.sys.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-19
 */
@RestController
@RequestMapping("/basicDivisionOrganization")
@Api(description = "防汛资源-防汛指挥部")
public class BasicDivisionOrganizationController {
    @Resource
    private BasicDivisionOrganizationService basicDivisionOrganizationService;
    @Resource
    private UserDepartmentService userDepartmentService;
    @Resource
    private SysUserService sysUserService;
    @Resource
    private BasicCommandDepartmentService basicCommandDepartmentService;
    @Resource
    private BasicAdministrativeDivisionService basicAdministrativeDivisionService;

    @GetMapping(value = "/selectAllOrganizationAndDepertment")
    @ApiOperation(value = "获得所有指挥部下部门下拉列表")
    @Log(desc = "获得所有指挥部下部门下拉列表")
    public Result selectAllOrganizationAndDepertment() {
        return Result.success(basicDivisionOrganizationService.selectAllOrganizationAndDepertment());
    }


    @GetMapping(value = "/selectAllDepartmentAndUser")
    @ApiOperation(value = "获得某个部门下成员列表")
    @Log(desc = "获得某个部门下成员拉列表")
    public Result selectAllOrganizationAndDepertment(Long id) {
        return Result.success(basicDivisionOrganizationService.selectAllOrganizationAndDepertment1(id));
    }


    @PostMapping(value = "/saveOrUpdateType")
    @ApiOperation(value = "新增或修改指挥部")
    @Log(desc = "新增或修改指挥部")
    public Result saveOrUpdateType(@RequestBody @Valid BasicCommandDepartment basicCommandDepartment) {
        if (basicCommandDepartmentService.saveOrUpdate(basicCommandDepartment)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }

    @DeleteMapping(value = "/deleteType/{id}")
    @ApiOperation(value = "删除指挥部")
    @Log(desc = "删除指挥部")
    @ApiImplicitParams(@ApiImplicitParam(name = "id", value = "指挥部主键"))
    public Result deleteType(@PathVariable("id") Long id) {
        List<BasicDivisionOrganization> responseActions = basicDivisionOrganizationService
                .list(new LambdaQueryWrapper<BasicDivisionOrganization>().eq(BasicDivisionOrganization::getCommandId, id));
        if (null != responseActions && responseActions.size() > 0) {
            return Result.fail("该指挥部下还有部门！");
        }
        if (basicCommandDepartmentService.removeById(id)) {
            return Result.success(ExceptionEnum.DELETE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
    }

    @GetMapping(value = "/getAllType")
    @ApiOperation(value = "获得所有指挥部列表")
    @Log(desc = "获得所有指挥部列表")
    public Result getAllType() {
        return Result.success(basicCommandDepartmentService.list(new LambdaQueryWrapper<BasicCommandDepartment>()
                .select(BasicCommandDepartment::getId, BasicCommandDepartment::getName)));
    }

    /**
     * 在某个指挥部下添加或修改部门
     *
     * @param basicDivisionOrganization
     * @return
     */
    @PostMapping(value = "/addOrUpdate")
    @ApiOperation(value = "在某个指挥部下添加或修改部门")
    @Log(desc = "在某个指挥部下添加或修改部门")
    Result addOrUpdateDivisionOrganization(@RequestBody @Valid BasicDivisionOrganization basicDivisionOrganization) {
        List<BasicAdministrativeDivision> basicAdministrativeDivisions = basicAdministrativeDivisionService.checkAreaName(basicDivisionOrganization.getAreaName());
        List<SysUser> sysUsers = sysUserService.checkUserName(basicDivisionOrganization.getDutyPeopleName());
        boolean e = basicDivisionOrganizationService.checkNameIsSame(basicDivisionOrganization);
        if (e) {
            basicDivisionOrganization.setAdministrativeDivisionId(basicAdministrativeDivisions.get(0).getId());
            basicDivisionOrganization.setUserId(sysUsers.get(0).getId());
            boolean f = basicDivisionOrganizationService.saveOrUpdate(basicDivisionOrganization);
            if (f) {
                return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS, basicDivisionOrganization.getId());
            } else {
                return Result.fail(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
            }
        } else {
            return Result.fail("该部门已存在！");
        }
    }

    /**
     * 获得 防汛部门
     *
     * @return
     */
    @GetMapping(value = "/getTree")
    @ApiOperation(value = "获得 某防汛指挥部下的部门信息并分页")
    @Log(desc = "获得 某防汛指挥部下的部门信息并分页")
    Result getDivisionOrganizationList(Page<BasicDivisionOrganizationDTO> page, Long id, String keywords) {
        return Result.success(basicDivisionOrganizationService.selectTree(page, id, keywords));
    }


    @ApiOperation(value = "部门信息excel导出")
    @GetMapping("/excel")
    @Log(desc = "部门信息excel导出")
    public void excel(HttpServletResponse response, Long id, String keywords) {
        try {
            basicDivisionOrganizationService.exportExcel(response, id, keywords);
        } catch (IOException e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_EXPORT_FAIL);
        }
    }

    @GetMapping(value = "/selectDepartmentById")
    @ApiOperation(value = "根据部门id获得部门信息")
    @Log(desc = "根据部门id获得部门信息")
    public Result selectDepartmentById(Long id) {
        return Result.success(basicDivisionOrganizationService.selectByDepartmentId(id));
    }

    @ApiOperation(value = "删除部门")
    @DeleteMapping("/deleteOrganization/{id}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "部门的主键")
    })
    @Log(desc = "删除部门")
    public Result deleteDivision(@PathVariable("id") String id) {
        List<Long> idList = new ArrayList<>();
        if (StrUtil.isNotBlank(id)) {
            idList = Arrays.stream(StrUtil.splitToLong(id, StrUtil.COMMA)).boxed().collect(Collectors.toList());

        }
        for (Long id1 : idList) {
            //首先这里要确定  是直接删掉整个指挥部  与该指挥部是否有成员无关
            List<UserDepartment> userDepartments =
                    userDepartmentService.list(new LambdaQueryWrapper<UserDepartment>().eq(UserDepartment::getOrganizationId, id1));
            if (null != userDepartments && userDepartments.size() > 0) {
                return Result.fail("该部门下还有人员！");
            }
        }
        if (basicDivisionOrganizationService.removeByIds(idList)) {
            return Result.success("删除成功");
        }
        throw new UnifiedException("删除失败");

    }

    @PostMapping(value = "/saveOrUpdatePeople")
    @ApiOperation(value = "给某个部门添加人员")
    @Log(desc = "给某个部门添加人员")
    public Result saveOrUpdate(@RequestBody @Valid UserDepartmentDTO userDepartmentDTO) {
        if (null == userDepartmentDTO.getUserName() && "".equals(userDepartmentDTO.getUserName())) {
            return Result.fail("请填姓名！");
        }
        List<SysUser> sysUsers = sysUserService.list(new QueryWrapper<SysUser>().lambda().eq(SysUser::getName, userDepartmentDTO.getUserName()));
        if (sysUsers.size() == 0) {
            return Result.fail("此人不是系统用户，请先到“系统管理-用户管理”中添加！");
        }
        UserDepartment userDepartment = new UserDepartment();
        BeanUtils.copyProperties(userDepartmentDTO, userDepartment);

        userDepartment.setUserId(sysUsers.get(0).getId());
        //判断该部门下是否已有该用户
        boolean f = userDepartmentService.checkUser(userDepartment);
        if (f && userDepartmentService.saveOrUpdate(userDepartment)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }

    /**
     * 根据部门和 人员名称 获得 该部门下的人员信息
     *
     * @param divisionOrganizationId
     * @param keywords
     * @return
     */
    @GetMapping(value = "/selectByIdAndName")
    @ApiOperation(value = "获得 某个部门下的人员的信息并分页")
    @Log(desc = "获得 某个部门下的人员的信息并分页")
    public Result selectByIdAndName(Page<BasicDivisionOrganizationPeopleDTO> page, Long divisionOrganizationId, String keywords) {
        return Result.success(userDepartmentService.selectByIdAndName(page, divisionOrganizationId, keywords));
    }

    @ApiOperation(value = "删除某部门下的人员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ids", value = "部门成员的主键")
    })
    @DeleteMapping("/deletePeople/{ids}")
    @Log(desc = "删除某部门下的人员信息")
    public Result delete(@PathVariable("ids") String ids) {
        List<Long> idList = Arrays.stream(StrUtil.splitToLong(ids, StrUtil.COMMA)).boxed().collect(Collectors.toList());
        if (userDepartmentService.removeByIds(idList)) {
            return Result.success(ExceptionEnum.DELETE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
    }


}

