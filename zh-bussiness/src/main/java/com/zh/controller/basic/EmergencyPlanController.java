package com.zh.controller.basic;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.dto.basic.EmergencyPlanDTO;
import com.zh.entity.basic.BasicAdministrativeDivision;
import com.zh.entity.basic.EmergencyPlan;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.basic.BasicAdministrativeDivisionService;
import com.zh.service.basic.EmergencyPlanService;
import com.zh.service.basic.EmergencyPlanTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * <p>
 * 应急预案表 前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-28
 */
@RestController
@RequestMapping("/emergencyPlan")
@Api(description = "防汛资源-应急预案")
public class EmergencyPlanController {

    @Resource
    private EmergencyPlanService emergencyPlanService;
    @Resource
    private EmergencyPlanTypeService emergencyPlanTypeService;
    @Resource
    private BasicAdministrativeDivisionService basicAdministrativeDivisionService;

    @PostMapping(value = "/saveOrUpdate")
    @ApiOperation(value = "新增或修改应急预案信息")
    @Log(desc = "新增或修改应急预案信息")
    public Result saveOrUpdate(@RequestBody @Valid EmergencyPlan emergencyPlan) {
        if (null==emergencyPlan.getAreaId()) {
           return Result.fail("请传行政区id！");
        }
        BasicAdministrativeDivision basicAdministrativeDivision = basicAdministrativeDivisionService.getById(emergencyPlan.getAreaId());
        emergencyPlan.setAreaCode(basicAdministrativeDivision.getDivisionCode());
        if (emergencyPlanService.saveOrUpdate(emergencyPlan)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }


    @ApiOperation(value = "删除应急预案")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "应急预案主键")
    })
    @DeleteMapping("/delete/{id}")
    @Log(desc = "删除应急预案")
    public Result delete(@PathVariable("id") Long id) {
        if (emergencyPlanService.removeById(id)) {
            return Result.success(ExceptionEnum.DELETE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
    }

    @GetMapping(value = "/selectAllByMessage")
    @ApiOperation(value = "根据搜索条件 分页获取应急预案信息")
    @Log(desc = "根据搜索条件 分页获取应急预案信息")
    public Result selectAllByMessage(Page<EmergencyPlanDTO> page, Long id, String keywords) {
        return Result.success(emergencyPlanService.selectAllByTypeIdAndName(page, id, keywords));
    }


//    @GetMapping(value = "/selectAllByMessage")
//    @ApiOperation(value = "根据搜索条件 分页获取应急预案信息")
//    @Log(desc = "根据搜索条件 分页获取应急预案信息")
//    public Result selectAllByMessage(Page<EmergencyPlanDTO> page, Long planTypeId, String planName) {
//        return Result.success(emergencyPlanService.selectAllByTypeIdAndName(page, planTypeId, planName));
//    }
//
//
//    @PostMapping(value = "/saveOrUpdateType")
//    @ApiOperation(value = "新增或修改应急预案类型信息")
//    @Log(desc = "新增或修改应急预案类型信息")
//    public Result saveOrUpdateType(@RequestBody @Valid EmergencyPlanType emergencyPlanType) {
//        if (emergencyPlanTypeService.saveOrUpdate(emergencyPlanType)) {
//            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
//        }
//        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
//    }
//
//    @ApiOperation(value = "删除应急预案类型")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "应急预案类型主键")
//    })
//    @DeleteMapping("/deleteType/{id}")
//    @Log(desc = "删除应急预案类型")
//    public Result deleteType(@PathVariable("id") Long id) {
//        boolean f = emergencyPlanTypeService.removeById(id);
//        boolean d = emergencyPlanService.deletedByPlanTypeId(id);
//        if (emergencyPlanTypeService.removeById(id) && emergencyPlanService.deletedByPlanTypeId(id)) {
//            return Result.success(ExceptionEnum.DELETE_SUCCESS);
//        }
//        throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
//    }
//
//    @GetMapping(value = "/selectAllType")
//    @ApiOperation(value = "获取所有预案类型 下拉列表")
//    @Log(desc = "获取所有预案类型 下拉列表")
//    public Result selectAllType() {
//        return Result.success(emergencyPlanTypeService.list());
//    }


}

