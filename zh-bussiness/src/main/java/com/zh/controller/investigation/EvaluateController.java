package com.zh.controller.investigation;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.api.Result;
import com.zh.entity.investigation.FloodThreatenWarning;
import com.zh.entity.investigation.LowEarlyWarning;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.investigation.FloodThreatenWarningService;
import com.zh.service.investigation.LowEarlyWarningService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 调查评价controller
 *
 * @author  赵梦霞
 * @since 2019-01-08 18:36

 **/
@RestController
@RequestMapping("/evaluate")
@Api(description = "调查评价结果")
public class EvaluateController {

    @Resource
    private LowEarlyWarningService lowEarlyWarningService;
    @Resource
    private FloodThreatenWarningService floodThreatenWarningService;

    @ApiOperation(value = "低洼易涝村落雨量预警指标表导入")
    @PostMapping("/prone/import")
    public Result prone(MultipartFile file) {
        if (lowEarlyWarningService.importExcel(file)) {
            return Result.success(ExceptionEnum.EXCEL_IMPORT_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
    }

    @ApiOperation(value = "新增或更改低洼易涝村落雨量预警指标表数据")
    @PostMapping("/prone/saveOrUpdate")
    public Result proneSaveOrUpdate(@RequestBody LowEarlyWarning lowEarlyWarning) {
        if (lowEarlyWarningService.saveOrUpdate(lowEarlyWarning)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }

    @ApiOperation(value = "删除低洼易涝村落雨量预警指标表数据")
    @DeleteMapping("/prone/delete/{ids}")
    public Result proneDelete(@PathVariable("ids") String ids) {
        List<Long> idList = Arrays.stream(StrUtil.splitToLong(ids, StrUtil.COMMA)).boxed().collect(Collectors.toList());
        if (lowEarlyWarningService.removeByIds(idList)) {
            return Result.success(ExceptionEnum.DELETE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
    }


    @ApiOperation(value = "外洪威胁村落水位预警指标表导入")
    @PostMapping("/waterLevel/import")
    public Result waterLevel(MultipartFile file) {
        if (floodThreatenWarningService.importExcel(file)) {
            return Result.success(ExceptionEnum.EXCEL_IMPORT_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.EXCEL_IMPORT_FAIL);
    }

    @ApiOperation(value = "新增或更改外洪威胁村落水位预警指标表")
    @PostMapping("/waterLevel/saveOrUpdate")
    public Result waterLevelSaveOrUpdate(@RequestBody FloodThreatenWarning floodThreatenWarning) {
        if (floodThreatenWarningService.saveOrUpdate(floodThreatenWarning)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }

    @ApiOperation(value = "删除外洪威胁村落水位预警指标表")
    @DeleteMapping("/waterLevel/delete/{ids}")
    public Result waterLevelDelete(@PathVariable("ids") String ids) {
        List<Long> idList = Arrays.stream(StrUtil.splitToLong(ids, StrUtil.COMMA)).boxed().collect(Collectors.toList());
        if (floodThreatenWarningService.removeByIds(idList)) {
            return Result.success(ExceptionEnum.DELETE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
    }

    @GetMapping(value = "/prone/getListPage")
    @ApiOperation(value = "获取低洼易涝村落雨量预警指标表数据分页")
    public Result getProneListPage(Page<LowEarlyWarning> page, String keywords){
        return lowEarlyWarningService.getListPage(page, keywords);
    }

    @GetMapping(value = "waterLevel/getListPage")
    @ApiOperation(value = "获取外洪威胁村落水位预警指标表分页")
    public Result getWaterLevelListPage(Page<FloodThreatenWarning> page, String keywords){
        return floodThreatenWarningService.getListPage(page, keywords);
    }

    @GetMapping(value = "/waterLevel/export")
    @ApiOperation(value = "导出外洪威胁村落水位预警指标表")
    public void exportFloodThreatenWarning(HttpServletResponse response, String keywords){
        floodThreatenWarningService.exportFloodThreatenWarning(response, keywords);
    }

    @GetMapping(value = "/prone/export")
    @ApiOperation(value = "导出低洼易涝村落雨量预警指标表")
    public void exportLowEarlyWarning(HttpServletResponse response, String keywords){
        lowEarlyWarningService.exportLowEarlyWarning(response, keywords);
    }

}
