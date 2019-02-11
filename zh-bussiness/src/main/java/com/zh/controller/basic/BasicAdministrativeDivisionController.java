package com.zh.controller.basic;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.dto.basic.AdministrativeIdAndCodeAndNameDTO;
import com.zh.dto.basic.BasicAdministrativeDivisionDTO;
import com.zh.entity.basic.BasicAdministrativeDivision;
import com.zh.entity.basic.BasicAdministrativeGrade;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.basic.BasicAdministrativeDivisionService;
import com.zh.service.basic.BasicAdministrativeGradeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-19
 */
@RestController
@RequestMapping("/basicAdministrativeDivision")
@Api(description = "行政区划")
public class BasicAdministrativeDivisionController {
    @Resource
    private BasicAdministrativeGradeService basicAdministrativeGradeService;
    @Resource
    private BasicAdministrativeDivisionService basicAdministrativeDivisionService;

    @GetMapping(value = "/selectAll")
    @ApiOperation(value = "行政级别下拉框")
    @Log(desc = "行政级别下拉框")
    public Result selectAll() {
        return Result.success(basicAdministrativeGradeService.list(new LambdaQueryWrapper<BasicAdministrativeGrade>()
                .select(BasicAdministrativeGrade::getId, BasicAdministrativeGrade::getName,BasicAdministrativeGrade::getType)));
    }

    @GetMapping(value = "/selectAllIdAndCodeAndName")
    @ApiOperation(value = "获得行政区 下拉列表(id,行政区代码，行政区名称)")
    @Log(desc = "获得行政区 下拉列表")
    public Result selectAllIdAndCodeAndName() {
        List<AdministrativeIdAndCodeAndNameDTO> administrativeIdAndCodeAndNameDTOS = new ArrayList<>();
        List<BasicAdministrativeDivision> basicAdministrativeDivisions = basicAdministrativeDivisionService.list();
        for (BasicAdministrativeDivision basicAdministrativeDivision : basicAdministrativeDivisions) {
            AdministrativeIdAndCodeAndNameDTO administrativeIdAndCodeAndNameDTO = new AdministrativeIdAndCodeAndNameDTO();
            administrativeIdAndCodeAndNameDTO.setAreaCode(basicAdministrativeDivision.getDivisionCode());
            administrativeIdAndCodeAndNameDTO.setAreaName(basicAdministrativeDivision.getDivisionName());
            administrativeIdAndCodeAndNameDTO.setId(basicAdministrativeDivision.getId());
            administrativeIdAndCodeAndNameDTO.setGrade(basicAdministrativeDivision.getDivisionGrade());
            administrativeIdAndCodeAndNameDTOS.add(administrativeIdAndCodeAndNameDTO);
        }

        return Result.success(administrativeIdAndCodeAndNameDTOS);
    }

    /**
     * 新增或修改行政区
     *
     * @return
     */
    @PostMapping(value = "/addOrUpdate")
    @ApiOperation(value = "新增或修改行政区.")
    @Log(desc = "新增或修改行政区")
    Result addOrUpdateAdministrative(@RequestBody @Valid BasicAdministrativeDivision basicAdministrativeDivision) {
//        boolean a = basicAdministrativeDivisionService.checkGrade(basicAdministrativeDivision);
        //首先判断  行政区名称不能重复  根据名称去查找
        boolean e = basicAdministrativeDivisionService.checkNameIsSame(basicAdministrativeDivision);
        boolean g = basicAdministrativeDivisionService.checkCodeIsSame(basicAdministrativeDivision);
        if ( e && g) {
            if (basicAdministrativeDivisionService.saveOrUpdate(basicAdministrativeDivision)) {
                return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
            }
            throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
        } else {
            return Result.fail("该行政区名称或代码已存在！");
        }
    }

    /**
     * 树形结构
     *
     * @return
     */
    @GetMapping("/getList")
    @ApiOperation(value = "获得行政区树形结构.")
    @Log(desc = "获得行政区树形结构")
    Result getAdministrativeList() {
        return Result.success(basicAdministrativeDivisionService.selectTree());
    }

    /**
     * 获得行政区列表  根据行政区名称进行搜索并且 分页
     *
     * @return
     */
    @GetMapping("/getTableList")
    @ApiOperation(value = "获得行政区列表.")
    @Log(desc = "获得行政区列表")
    Result getAdministrativeTableList(Page<BasicAdministrativeDivisionDTO> page, String keywords, Long id) {
        if (id == null) {
            return Result.success("请传行政区id");
        }
        return Result.success(basicAdministrativeDivisionService.queryPage(page, keywords, id));
    }

    @ApiOperation(value = "单个删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键")
    })
    @DeleteMapping("/delete/{id}")
    @Log(desc = "删除行政区")
    public Result deleteDivision(@PathVariable("id") Long id) {
        //首先需要判断 将要删除的 行政区 有无其他行政区以它为父节点的。如果有则提示先将该行政区下的给删除，否则删除
        List<BasicAdministrativeDivisionDTO> basicAdministrativeDivisions =
                basicAdministrativeDivisionService.selectByParentId(id);
        if (basicAdministrativeDivisions != null && basicAdministrativeDivisions.size() > 0) {
            return Result.fail("请先删除其下级行政区！");
        } else {
            if (basicAdministrativeDivisionService.removeById(id)) {
                return Result.success("删除成功");
            }
        }
        throw new UnifiedException("删除失败");
    }

}

