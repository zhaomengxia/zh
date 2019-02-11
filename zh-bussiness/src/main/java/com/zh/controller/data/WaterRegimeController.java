package com.zh.controller.data;

import com.zh.api.Result;
import com.zh.dto.data.QueryDTO;
import com.zh.enums.ExceptionEnum;
import com.zh.exceptions.UnifiedException;
import com.zh.service.data.WaterRegimeService;
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
 * 水情controller
 *
 * @author  赵梦霞
 * @since 2019-01-07 10:58

 **/
@RestController
@RequestMapping("/waterRegime")
@Api(description = "水情监控")
public class WaterRegimeController extends DataController{

    @Resource
    private WaterRegimeService waterRegimeService;

    @ApiOperation(value = "水位信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "time", value = "查询时间点时间戳（必填）"),
    })
    @GetMapping("/list")
    public Result list(QueryDTO queryDTO) {
        if (queryDTO.getTime() == null) {
            throw new UnifiedException("缺少时间点参数");
        }
        return Result.success(waterRegimeService.list(queryDTO));
    }

    @ApiOperation(value = "水位信息详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "站点主键（必填）"),
            @ApiImplicitParam(name = "start", value = "开始时间戳（必填）"),
            @ApiImplicitParam(name = "end", value = "结束时间戳（必填）")
    })
    @GetMapping("/section")
    public Result section(QueryDTO queryDTO) {
        parameterCheck(queryDTO);
        return Result.success(waterRegimeService.section(queryDTO));
    }


    @ApiOperation(value = "导出")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "time", value = "查询时间点时间戳（必填）"),
    })
    @GetMapping("/export")
    public void export(HttpServletResponse response, QueryDTO queryDTO) {
        if (queryDTO.getTime() == null) {
            throw new UnifiedException("缺少时间点参数");
        }
        try {
            waterRegimeService.export(response, queryDTO);
        } catch (IOException e) {
            throw new UnifiedException(ExceptionEnum.EXCEL_EXPORT_FAIL);
        }
    }

}
