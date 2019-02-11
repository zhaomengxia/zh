package com.zh.controller.meteorological;

import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.service.meteorological.MeteorologicalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 气象控制器
 *
 * @author  赵梦霞
 * @since 2018-12-22 15:54

 **/
@RestController
@Api(description = "气象预警")
public class MeteorologicalController {

    @Resource
    private MeteorologicalService meteorologicalService;

    @ApiOperation(value = "获取暴雨预警")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cityName", value = "城市名称")
    })
    @Log(desc = "获取暴雨预警")
    @GetMapping("/rainStorm/warn")
    public Result meteorological(String cityName) {
        return Result.success(meteorologicalService.getRainStormWarn(cityName));
    }

}
