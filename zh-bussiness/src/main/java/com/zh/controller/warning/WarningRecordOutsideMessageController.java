package com.zh.controller.warning;


import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.service.warning.WarningRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 警情中心信息 前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-13
 */
@RestController
@RequestMapping("/warning")
@Api(description = "警情中心")
public class WarningRecordOutsideMessageController {

    @Resource
    private WarningRecordService warningRecordService;

    @GetMapping(value = "/selectAllWarnRecord")
    @ApiOperation(value = "获得自动报警信息")
    @Log(desc = "获得自动报警信息")
    public Result selectAllWarnRecord(){
        return Result.success(warningRecordService.selectAllWarnRecord(null,null,null,null,null));
    }
}

