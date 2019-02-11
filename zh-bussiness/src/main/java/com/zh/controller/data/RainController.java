package com.zh.controller.data;

import com.zh.api.Result;
import com.zh.dto.data.QueryDTO;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.data.RainService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 雨量controller
 *
 * @author  赵梦霞
 * @since 2019-01-04 13:59

 **/
@RestController
@RequestMapping("/rain")
@Api(description = "雨情监视")
public class RainController extends DataController {

    @Resource
    private RainService rainService;

    @ApiOperation(value = "雨量信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "开始时间戳（选填）"),
            @ApiImplicitParam(name = "end", value = "结束时间戳（选填）")
    })
    @GetMapping("/list")
    public Result list(QueryDTO queryDTO) {
        return Result.success(rainService.list(queryDTO));
    }

    @ApiOperation(value = "雨量强度统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "开始时间戳（选填）"),
            @ApiImplicitParam(name = "end", value = "结束时间戳（选填）")
    })
    @GetMapping("/strength")
    public Result strength(QueryDTO queryDTO) {
        return Result.success(rainService.strength(queryDTO));
    }


    @ApiOperation(value = "过程线")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "站点主键（必填）"),
            @ApiImplicitParam(name = "start", value = "开始时间戳（必填）"),
            @ApiImplicitParam(name = "end", value = "结束时间戳（必填）")
    })
    @GetMapping("/polyline")
    public Result polyline(QueryDTO queryDTO) {
        parameterCheck(queryDTO);
        return Result.success(rainService.polyline(queryDTO));
    }

    @ApiOperation(value = "柱状图")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "站点主键（必填）"),
            @ApiImplicitParam(name = "time", value = "查询时间点（必填）"),
    })
    @GetMapping("/histogram")
    public Result histogram(QueryDTO queryDTO) {
        if (queryDTO.getId() == null) {
            throw new UnifiedException("站点主键参数");
        }
        if (queryDTO.getTime() == null) {
            throw new UnifiedException("缺少时间点参数");
        }
        return Result.success(rainService.histogram(queryDTO));
    }

    @ApiOperation(value = "导出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "开始时间戳（选填）"),
            @ApiImplicitParam(name = "end", value = "结束时间戳（选填）")
    })
    @GetMapping("/export")
    public void export(HttpServletResponse response, QueryDTO queryDTO) {
        try {
            rainService.export(response, queryDTO);
        } catch (IOException e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_EXPORT_FAIL);
        }
    }


}