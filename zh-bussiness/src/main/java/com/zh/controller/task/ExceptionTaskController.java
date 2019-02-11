package com.zh.controller.task;

import com.zh.api.Result;
import com.zh.entity.task.ExceptionJob;
import com.zh.entity.task.ExceptionRecord;
import com.zh.exceptions.UnifiedException;
import com.zh.service.task.ExceptionTaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 异常处理controller
 *
 * @author  赵梦霞
 * @since 2019-01-14 10:06

 **/
@RestController
@RequestMapping("/exception")
@Api(description = "异常相关")
public class ExceptionTaskController {

    @Resource
    private ExceptionTaskService exceptionTaskService;

    //------------------------------------上报组-------------------------------

    @ApiOperation(value = "异常上报")
    @PostMapping("/user/report")
    public Result report(@RequestBody ExceptionRecord record) {
        if (exceptionTaskService.export(record)) {
            return Result.success("上报异常成功");
        }
        throw new UnifiedException("上报异常失败");
    }

    @ApiOperation(value = "新上报异常查询")
    @GetMapping("/user/new")
    public Result newExceptionQuery() {
        return Result.success(exceptionTaskService.queryNewException());
    }

    @ApiOperation(value = "查询任务")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "1：代办任务2：完成任务"),
    })
    @GetMapping("/user/task")
    public Result taskQuery(Integer status) {
        return Result.success(exceptionTaskService.queryTask(status));
    }

    @ApiOperation(value = "完成任务")
    @PostMapping("/user/task/complete")
    public Result taskComplete(@RequestBody ExceptionJob exceptionJob) {
        if (exceptionTaskService.complete(exceptionJob)) {
            return Result.success("任务反馈成功");
        }
        throw new UnifiedException("任务反馈失败");
    }

    //-------------------------------------管理组---------------------------------

    @ApiOperation(value = "新上报异常查询")
    @GetMapping("/manager/new")
    public Result newExceptionQueryList() {
        return Result.success(exceptionTaskService.queryNewExceptionList());
    }

    @ApiOperation(value = "任务指派")
    @PostMapping("/manager/assign")
    public Result assign(@RequestBody ExceptionJob job) {
        if (exceptionTaskService.assign(job)) {
            return Result.success("任务指派成功");
        }
        throw new UnifiedException("任务指派失败");
    }

    @ApiOperation(value = "处置中异常任务查询")
    @GetMapping("/manager/handle")
    public Result handleQuery() {
        return Result.success(exceptionTaskService.queryHandleException(null));
    }

    @ApiOperation(value = "待审核异常任务查询")
    @GetMapping("/manager/review")
    public Result reviewQuery() {
        return Result.success(exceptionTaskService.queryReviewException(null));
    }

    @ApiOperation(value = "已完成任务查询")
    @GetMapping("/manager/complete")
    public Result completeQuery() {
        return Result.success(exceptionTaskService.queryCompleteException(null));
    }


    @ApiOperation(value = "审核任务")
    @PostMapping("/review")
    public Result reviewTask(Long exceptionId) {
        if (exceptionTaskService.review(exceptionId)) {
            return Result.success("审核任务成功");
        }
        throw new UnifiedException("审核任务失败");
    }


}
