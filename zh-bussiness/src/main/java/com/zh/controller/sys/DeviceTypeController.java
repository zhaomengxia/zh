package com.zh.controller.sys;


import com.zh.aop.annotation.Log;
import com.zh.api.Result;
import com.zh.service.sys.DeviceTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 设备类型表 前端控制器
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-22
 */
@RestController
@RequestMapping("/deviceType")
@Api(description = "获得设备类型下因子")
public class DeviceTypeController {
    @Resource
    private DeviceTypeService deviceTypeService;

    @GetMapping(value = "/selectByDeviceTypeId")
    @ApiOperation(value = "获得设备类型下的因子信息")
    @Log(desc = "获得设备类型下的因子信息")
    public Result selectByDeviceTypeId(Long deviceTypeId){
        return Result.success(deviceTypeService.selectByDeviceTypeId(deviceTypeId));
    }

}

