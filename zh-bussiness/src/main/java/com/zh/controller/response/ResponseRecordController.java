package com.zh.controller.response;


import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.entity.response.ResponseDisaterRecord;
import com.zh.entity.response.ResponseFeedbackRecord;
import com.zh.entity.response.ResponseRecord;
import com.zh.entity.warning.WarningDegree;
import com.zh.entity.warning.WarningRecord;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.response.*;
import com.zh.service.warning.WarningDegreeService;
import com.zh.service.warning.WarningRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 应急响应地图 前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-11
 */
@RestController
@RequestMapping("/responseMap")
@Api(description = "应急响应-响应地图")
public class ResponseRecordController {

    @Resource
    private ResponseRecordService responseRecordService;
    @Resource
    private ResponseActionMessageService responseActionMessageService;
    @Resource
    private ResponseFeedbackService responseFeedbackService;
    @Resource
    private ResponseDisasterService responseDisasterService;
    @Resource
    private WarningRecordService warningRecordService;
    @Resource
    private ResponseFeedbackRecordService responseFeedbackRecordService;
    @Resource
    private ResponseDisaterRecordService responseDisaterRecordService;
    @Resource
    private WarningDegreeService warningDegreeService;

    @PostMapping(value = "/feedback/saveOrUpdateResponseFeedbackRecord")
    @ApiOperation(value = "响应管理-响应反馈时")
    @Log(desc = "响应管理-响应反馈时")
    public Result saveOrUpdateResponseFeedbackRecord(@RequestBody @Valid ResponseFeedbackRecord responseFeedbackRecord) {
        boolean f=responseRecordService.checkstatus(responseFeedbackRecord.getResponseRecordId(),2);
        if (f&&responseFeedbackRecordService.saveOrUpdate(responseFeedbackRecord)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }

    @PostMapping(value = "/disater/saveOrUpdateResponseDisaterRecord")
    @ApiOperation(value = "响应管理-灾情统计时")
    @Log(desc = "响应管理-灾情统计时")
    public  Result saveOrUpdateResponseDisaterRecord(@RequestBody @Valid ResponseDisaterRecord responseDisaterRecord) {
        //在加入之前，先判断
        boolean f=responseRecordService.checkstatus(responseDisaterRecord.getResponseRecordId(),4);
        if (f&&responseDisaterRecordService.saveOrUpdate(responseDisaterRecord)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }


    @PostMapping(value = "/action/saveOrUpdateWarnRecordById")
    @ApiOperation(value = "根据预警记录id来关闭预警状态（点关闭预警）")
    @Log(desc = "修改预警响应的状态")
    public Result saveOrUpdateWarnRecord(Long id) {
        WarningRecord warningRecord = warningRecordService.getById(id);
        warningRecord.setStatus(5);

        boolean f = responseRecordService.updateRecord(id);
        if (f && warningRecordService.updateById(warningRecord)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }


    @PostMapping(value = "/action/saveOrUpdateRecordById")
    @ApiOperation(value = "根据响应记录id关闭响应（点关闭响应按钮）")
    @Log(desc = "根据响应记录id关闭响应（点关闭响应按钮）")
    public Result saveOrUpdateResponseRecord(Long id) {
        ResponseRecord responseRecord = responseRecordService.getById(id);
        //判断是 否是响应启动直接跳到关闭响应 并记录
        if (responseRecord.getResponseStatus()==1){
            responseRecord.setJump(1);
        }
        responseRecord.setResponseStatus(3);
        if (responseRecordService.updateById(responseRecord)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }


    @GetMapping(value = "/selectAllActionMapPage")
    @ApiOperation(value = "响应地图 已启动响应列表")
    @Log(desc = "响应地图 已启动响应列表")
    public Result selectAllActionPage() {
        return Result.success(responseActionMessageService.selectAllStartMapPage(null, null));
    }


    @GetMapping(value = "/selectAllActionPage")
    @ApiOperation(value = "响应地图 响应反馈中列表")
    @Log(desc = "响应地图 响应反馈中列表")
    public Result selectAllFeedbackPage() {
        return Result.success(responseFeedbackService.selectAllFeedbackMap(null, null));
    }

    @GetMapping(value = "/selectAllDisaterPage")
    @ApiOperation(value = "响应地图 灾情统计中列表")
    @Log(desc = "响应地图 灾情统计中中列表")
    public Result selectAllDisaterPage() {
        return Result.success(responseDisasterService.selectAllMap(null, null));
    }

    @GetMapping(value = "/selectAllMap")
    @ApiOperation(value = "响应地图 全部")
    @Log(desc = "响应地图 全部")
    public Result selectAllMap() {
        return Result.success(responseRecordService.selectAllMap());
    }

    /**
     * 获取预警等级列表
     * @return
     */
    @ApiOperation(value = "获取预警等级列表")
    @GetMapping(value = "/getWarningDegreeList")
    public List<WarningDegree> getWarningDegreeList(){
        return warningDegreeService.list();
    }

}

