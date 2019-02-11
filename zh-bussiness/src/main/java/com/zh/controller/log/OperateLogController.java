package com.zh.controller.log;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.dto.log.operate.OperateLogQuery;
import com.zh.entity.log.OperateLog;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.log.OperateLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 操作日志表 前端控制器
 * </p>
 *
 * @author  赵梦霞
 * @since 2018-12-18
 */
@RestController
@RequestMapping("/operateLog")
@Api(description = "操作日志")
public class OperateLogController {

    @Resource
    private OperateLogService operateLogService;

    @GetMapping("/page")
    @Log(desc = "查询操作日志列表")
    @ApiOperation(value = "查询操作日志列表")
    public Result page(Page<OperateLog> page, OperateLogQuery query) {
        return Result.success(operateLogService.queryPage(page, query));
    }

    @ApiOperation(value = "导出操作日志excel")
    @GetMapping("/excel")
    @Log(desc = "导出操作日志excel")
    public void excel(HttpServletResponse response, OperateLogQuery query) {
        try {
            operateLogService.export(response, query);
        } catch (IOException e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_EXPORT_FAIL);
        }
    }

}

