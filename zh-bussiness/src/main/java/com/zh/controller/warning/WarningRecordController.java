package com.zh.controller.warning;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.dto.warning.WarningRecordDTO;
import com.zh.entity.response.ResponseRecord;
import com.zh.entity.warning.*;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.flood.FloodPreventionOwnerService;
import com.zh.service.response.ResponseRecordService;
import com.zh.service.warning.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 预警记录表 前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-27
 */
@RestController
@RequestMapping("/warningRecord")
@Api(description = "预警发布-预警管理")
public class WarningRecordController {

    @Resource
    private WarningRecordService warningRecordService;
    @Resource
    private WarningRecordInsideMessageService warningRecordInsideMessageService;
    @Resource
    private WarningRecordOutsideMessageService warningRecordOutsideMessageService;
    @Resource
    private WarningStatusService warningStatusService;
    @Resource
    private InnerWarningRecordService innerWarningRecordService;
    @Resource
    private OuterWarningRecordService outerWarningRecordService;
    @Resource
    private ResponseRecordService responseRecordService;
    @Resource
    private FloodPreventionOwnerService floodPreventionOwnerService;

    @GetMapping(value = "/selectAllWarningStatus")
    @ApiOperation(value = "预警状态下拉列表")
    @Log(desc = "预警状态下拉列表")
    public Result selectAllWarningStatus(){
        return Result.success(warningStatusService.list(new LambdaQueryWrapper<WarningStatus>()
                .select(WarningStatus::getValue,WarningStatus::getName)).stream().filter(a->a.getValue()!=5).filter(a->a.getValue()!=4).collect(Collectors.toList()));
    }

    @GetMapping(value = "/selectAllWarnRecord")
    @ApiOperation(value = "获得预警管理列表并分页")
    @Log(desc = "获得预警管理列表并分页")
    public Result selectAllWarnRecord(Page<WarningRecordDTO> page,String areaCode,String keywords,Long startTime,Long endTime,Integer value){
      return Result.success(warningRecordService.selectAllWarnRecord(page,areaCode,keywords,startTime,endTime,value));
    }



    @PostMapping(value = "/saveOrUpdateInsideWarn")
    @ApiOperation(value = "内部预警-发布预警时")
    @Log(desc = "内部预警-发布预警时")
    public Result saveOrUpdateInsideWarn(@RequestBody @Valid InnerWarningRecord innerWarningRecord){
        boolean f=warningRecordService.updateWarnSattus(innerWarningRecord.getWarningRecordId(),2);
        if (f&&innerWarningRecordService.saveOrUpdate(innerWarningRecord)){
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }


    @PostMapping(value = "/saveOrUpdateOutsideWarn")
    @ApiOperation(value = "外部预警-发布预警时")
    @Log(desc = "外部预警-发布预警时")
    public Result saveOrUpdateOutsideWarn(@RequestBody @Valid OuterWarningRecord outerWarningRecord){
        boolean f=warningRecordService.updateWarnSattus(outerWarningRecord.getWarningRecordId(),3);
        if (f&&outerWarningRecordService.saveOrUpdate(outerWarningRecord)){
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }



    @PostMapping(value = "/saveOrUpdateResponseRecord")
    @ApiOperation(value = "响应启动时")
    @Log(desc = "响应启动")
    public Result saveOrUpdateResponseRecord(@RequestBody @Valid ResponseRecord responseRecord){
        boolean f=warningRecordService.updateWarnSattus(responseRecord.getWarningRecordId(),4);
        System.out.println(f);
        responseRecord.setResponseTime(Instant.now().toEpochMilli());
        if (f&&responseRecordService.saveOrUpdate(responseRecord)){
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }


    @PostMapping(value = "/closeWarn")
    @ApiOperation(value = "关闭预警时,传预警记录id")
    @Log(desc = "关闭预警时,传预警记录id")
    public synchronized Result closeWarn(Long id){
        WarningRecord warningRecord = warningRecordService.getById(id);
        //这里需要判断 是1新预警-5关闭预警，还是2已内部预警（外部预警）-5关闭预警，还是 3已外部预警（响应启动）-5关闭预警
        if (4-warningRecord.getStatus()>0) {
            //为1则是已外部预警时在响应启动与关闭预警之间选择了 关闭预警。为2则是 在已内部预警之后，接着点击了关闭预警，为3 是在新预警之后点击了关闭预警
            //新预警-外部预警3-关闭  越过内部预警，（响应启动） 为1  ；内部预警-关闭预警越过外部预警，（响应启动）是2；越过内部预警和外部预警，（响应启动）是3
            warningRecord.setJump(4 - warningRecord.getStatus());
        }
        warningRecord.setStatus(5);
        boolean f = responseRecordService.updateRecord(id);
        if (f&&warningRecordService.updateById(warningRecord)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }


    @PostMapping(value = "/saveOrUpdateInsideWarn/Message")
    @ApiOperation(value = "已内部预警-预警反馈时")
    @Log(desc = "已内部预警-预警反馈时")
    public Result saveOrUpdateInsideWarnMessage(@RequestBody @Valid WarningRecordInsideMessage warningRecordInsideMessage){
        List<InnerWarningRecord> innerWarningRecords= innerWarningRecordService.list(new LambdaQueryWrapper<InnerWarningRecord>().eq(InnerWarningRecord::getWarningRecordId,warningRecordInsideMessage.getWarnRecordId()));
        warningRecordInsideMessage.setWarnRecordId(innerWarningRecords.get(0).getWarningRecordId());
        if (warningRecordInsideMessageService.saveOrUpdate(warningRecordInsideMessage)){
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }



    @PostMapping(value = "/saveOrUpdateOutsideWarn/Message")
    @ApiOperation(value = "已外部预警-预警反馈时")
    @Log(desc = "已外部预警-预警反馈时")
    public Result saveOrUpdateOutsideWarnMessage(@RequestBody @Valid WarningRecordOutsideMessage warningRecordOutsideMessage){
        List<OuterWarningRecord> outerWarningRecords= outerWarningRecordService.list(new LambdaQueryWrapper<OuterWarningRecord>().eq(OuterWarningRecord::getWarningRecordId,warningRecordOutsideMessage.getWarnRecordId()));
        warningRecordOutsideMessage.setWarnRecordId(outerWarningRecords.get(0).getWarningRecordId());
        if (warningRecordOutsideMessageService.saveOrUpdate(warningRecordOutsideMessage)){
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }

    @GetMapping(value = "/selectAllWarnInsideMessageById")
    @ApiOperation(value = "获得已内部预警详情")
    public Result selectAllWarnInsideMessageById(Long id){
        return Result.success(warningRecordInsideMessageService.selectAllWarnInsideMessageById(id));
    }


    @GetMapping(value = "/selectAllWarnOutsideMessageById")
    @ApiOperation(value = "获得已外部预警详情")
    public Result selectAllWarnOutsideMessageById(Long id){
        return Result.success(warningRecordOutsideMessageService.selectAllWarnInsideMessageById(id));
    }



}

