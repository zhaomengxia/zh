package com.zh.controller.response;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.dto.response.*;
import com.zh.entity.response.*;
import com.zh.entity.warning.WarningRecord;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.response.*;
import com.zh.service.warning.WarningRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 灾情统计 前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-07
 */
@RestController
@RequestMapping("/response")
@Api(description = "应急响应-响应管理")
public class ResponseDisasterController {

    @Resource
    private ResponseDisasterService responseDisasterService;
    @Resource
    private ResponseFeedbackService responseFeedbackService;
    @Resource
    private ResponseActionMessageService responseActionMessageService;
    @Resource
    private ResponseRecordService responseRecordService;
    @Resource
    private WarningRecordService warningRecordService;
    @Resource
    private ResponseDegreeService responseDegreeService;
    @Resource
    private ResponseFeedbackRecordService responseFeedbackRecordService;
    @Resource
    private ResponseDisaterRecordService responseDisaterRecordService;

    @GetMapping(value = "/selectAllDegree")
    @ApiOperation(value = "获取响应等级下拉列表")
    @Log(desc = "获取响应等级下拉列表")
    public Result selectAllDegree() {
        return Result.success(responseDegreeService.list(new LambdaQueryWrapper<ResponseDegree>().select(ResponseDegree::getStatus, ResponseDegree::getName)));
    }

    @PostMapping(value = "/action/saveOrUpdateResponseFeedback")
    @ApiOperation(value = "响应管理-响应反馈时")
    @Log(desc = "响应管理-响应反馈时")
    public Result saveOrUpdateResponseFeedback(@RequestBody @Valid ResponseFeedbackRecord responseFeedbackRecord) {
        boolean f=responseRecordService.checkstatus(responseFeedbackRecord.getResponseRecordId(),2);
        if (f&&responseFeedbackRecordService.saveOrUpdate(responseFeedbackRecord)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }

    @PostMapping(value = "/action/saveOrUpdateResponseRecord")
    @ApiOperation(value = "响应管理-灾情统计时")
    @Log(desc = "响应管理-灾情统计时")
    public Result saveOrUpdateResponseRecord(@RequestBody @Valid ResponseDisaterRecord responseDisaterRecord) {
        //在加入之前，先判断
        boolean f=responseRecordService.checkstatus(responseDisaterRecord.getResponseRecordId(),4);
        if (f&&responseDisaterRecordService.saveOrUpdate(responseDisaterRecord)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }

    @PostMapping(value = "/action/saveOrUpdateWarnRecordById")
    @ApiOperation(value = "根据预警记录id来关闭预警状态（点关闭预警）")
    @Log(desc = "修改预警的状态")
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
        if (responseRecord.getResponseStatus() == 1) {
            responseRecord.setJump(1);
        }
        responseRecord.setResponseStatus(3);
        if (responseRecordService.updateById(responseRecord)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }


    @GetMapping(value = "/action/saveOrUpdateRecordById")
    @ApiOperation(value = "根据预警记录id  查看响应启动信息type传1 响应反馈信息传2 灾情统计响应信息 type传3")
    @Log(desc = "根据预警记录id查看响应启动信息")
    public Result selectResponseRecordStartByWarnId(Long id,Integer type) {
       return Result.success(responseRecordService.selectResponseStartByWarnId(id,type));
    }



    @GetMapping(value = "/selectAllManage")
    @ApiOperation(value = "获得预警响应信息 并分页")
    @Log(desc = "获得预警响应信息 并分页")
    public Result selectAllManage(Page<ResponseManageDTO> page, String keywords) {
        return Result.success(responseRecordService.selectAll(page, keywords));
    }

    @ApiOperation(value = "预警响应信息导出")
    @GetMapping("/excel")
    @Log(desc = "预警响应信息导出")
    public void excel(HttpServletResponse response, String keywords) {
        try {
            responseRecordService.exportExcel(response, keywords);
        } catch (IOException e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_EXPORT_FAIL);
        }
    }

    @PostMapping(value = "/action/saveOrUpdateActionMessage")
    @ApiOperation(value = "新增或修改某个响应下的响应相关信息")
    @Log(desc = "新增或修改某个响应下的响应相关信息")
    public Result saveOrUpdateActionMessage(@RequestBody @Valid ResponseActionMessage responseActionMessage) {
        if (responseActionMessageService.saveOrUpdate(responseActionMessage)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }


    @ApiOperation(value = "删除某个响应下的响应相关信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "某个响应下的反馈信息主键")
    })
    @DeleteMapping("/deleteAction/{id}")
    @Log(desc = "删除某个响应下的反馈信息")
    public Result deleteAction(@PathVariable("id") Long id) {
        if (responseActionMessageService.removeById(id)) {
            return Result.success(ExceptionEnum.DELETE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
    }

    @GetMapping(value = "/selectAllActionPage")
    @ApiOperation(value = "获得响应下的反馈信息(响应启动)列表")
    @Log(desc = "获得响应下的反馈信息(响应启动)列表")
    public Result selectAllActionPage(Page<ReponseActionMessageDTO> page, Long id, Boolean f) {
        return Result.success(responseActionMessageService.selectAllActionPage(page, id, f));
    }


    @PostMapping(value = "/disaster/saveOrUpdate")
    @ApiOperation(value = "新增或修改某个响应下的灾情统计")
    @Log(desc = "新增或修改某个响应下的灾情统计")
    public Result saveOrUpdateDisaster(@RequestBody @Valid ResponseDisaster responseDisaster) {
        //这里要通过响应记录id1查找  灾情统计记录表
        List<ResponseDisaterRecord> responseDisaterRecords= responseDisaterRecordService.list(new LambdaQueryWrapper<ResponseDisaterRecord>().eq(ResponseDisaterRecord::getResponseRecordId,responseDisaster.getWarnRecordId()));
        responseDisaster.setWarnRecordId(responseDisaterRecords.get(0).getId());
        if (responseDisasterService.saveOrUpdate(responseDisaster)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }

    @ApiOperation(value = "删除某个预警下某灾情统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "灾情统计主键")
    })
    @DeleteMapping("/deleteDisaster/{id}")
    @Log(desc = "删除某个响应下某灾情统计")
    public Result deleteDisaster(@PathVariable("id") Long id) {
        if (responseDisasterService.removeById(id)) {
            return Result.success(ExceptionEnum.DELETE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
    }

    @GetMapping(value = "/selectAllDisaster")
    @ApiOperation(value = "获得灾情统计信息列表(预警记录id)")
    @Log(desc = "获得灾情统计信息列表")
    public Result selectAllPage(Page<ResponseDisaterDTO> page, Long id, Boolean f) {
        return Result.success(responseDisasterService.selectAllById(page, id, f));
    }


    @ApiOperation(value = "灾情统计信息导出")
    @GetMapping("/excelDisater")
    @Log(desc = "灾情统计信息导出")
    public void excelDisater(HttpServletResponse response, Long id, Boolean f) {
        try {
            responseDisasterService.exportExcel(response, id, f);
        } catch (IOException e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_EXPORT_FAIL);
        }
    }

    @PostMapping(value = "/feedback/saveOrUpdate")
    @ApiOperation(value = "新增或修改某个预警下的响应反馈")
    @Log(desc = "新增或修改某个预警下的响应反馈")
    public synchronized Result saveOrUpdateFeedback(@RequestBody @Valid ResponseFeedback responseFeedback) {
        List<ResponseFeedbackRecord> responseFeedbackRecords = responseFeedbackRecordService.list(new LambdaQueryWrapper<ResponseFeedbackRecord>().eq(ResponseFeedbackRecord::getResponseRecordId, responseFeedback.getWarnRecordId()));
        responseFeedback.setWarnRecordId(responseFeedbackRecords.get(0).getId());
        if (responseFeedbackService.saveOrUpdate(responseFeedback)) {
            return Result.success(ExceptionEnum.SAVE_OR_UPDATE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.SAVE_OR_UPDATE_FAIL);
    }

    @ApiOperation(value = "删除某个预警下某响应反馈")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "响应反馈主键")
    })
    @DeleteMapping("/deleteFeedback/{id}")
    @Log(desc = "删除某个预警下某响应反馈")
    public synchronized Result deleteFeedback(@PathVariable("id") Long id) {
        if (responseFeedbackService.removeById(id)) {
            return Result.success(ExceptionEnum.DELETE_SUCCESS);
        }
        throw new UnifiedException(ExceptionEnum.DELETE_FAIL);
    }

    @GetMapping(value = "/selectAllFeedback")
    @ApiOperation(value = "获得响应反馈信息列表(预警记录id)")
    @Log(desc = "获得响应反馈信息列表")
    public Result selectAllFeedback(Page<ResponseFeedbackDTO> page, Long id, Boolean f) {
        return Result.success(responseFeedbackService.selectAllFeedback(page, id, f));
    }


    @ApiOperation(value = "响应反馈信息导出")
    @GetMapping("/excelFeedback")
    @Log(desc = "响应反馈信息导出")
    public void excelFeedback(HttpServletResponse response, Long id, Boolean f) {
        try {
            responseFeedbackService.exportExcel(response, id, f);
        } catch (IOException e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_EXPORT_FAIL);
        }
    }

    @GetMapping(value = "/selectAllActionPage/two")
    @ApiOperation(value = "响应管理 已启动响应详情弹框")
    @Log(desc = "响应管理 已启动响应详情弹框")
    public Result selectAllActionMapPage(Long id) {
        return Result.success(responseActionMessageService.selectAllStartPage(id));
    }

}

