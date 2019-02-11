package com.zh.controller.warning;


import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.service.warning.WarningDegreeService;
import com.zh.service.warning.WarningRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 预警发布-预警地图 前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-13
 */
@RestController
@RequestMapping("/warningRecordInsideMessage")
@Api(description = "预警发布-预警地图")
public class WarningRecordInsideMessageController {
    @Resource
    private WarningDegreeService warningDegreeService;
    @Resource
    private WarningRecordService warningRecordService;

    @GetMapping(value = "/selectAllWarnRecord")
    @ApiOperation(value = "预警发布-预警地图")
    @Log(desc = "预警发布-预警地图")
    public Result selectAllWarnRecord(String areaCode, String keywords, Long startTime, Long endTime, Integer value){
        return Result.success(warningRecordService.selectAllWarnRecord(areaCode,keywords,startTime,endTime,value));
    }

    /**
     * 获取预警等级列表
     * @return
     */
    @ApiOperation(value = "获取预警等级列表")
    @GetMapping(value = "/getWarningDegreeList")
    public Result getWarningDegreeList(){
        return Result.success(warningDegreeService.list());
    }

    @GetMapping(value = "/getAll")
    @ApiOperation(value = "获得设备离线，正常个数")
    public Result getAll(){
        return Result.success(warningRecordService.getAll());
    }

}

