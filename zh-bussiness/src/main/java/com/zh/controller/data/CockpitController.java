package com.zh.controller.data;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zh.api.Result;
import com.zh.dto.data.NewWarningDataDTO;
import com.zh.dto.data.QueryDTO;
import com.zh.dto.data.RainListDTO;
import com.zh.dto.data.WaterListDTO;
import com.zh.entity.sys.Site;
import com.zh.service.data.CockpitService;
import com.zh.service.data.RainService;
import com.zh.service.data.WaterRegimeService;
import com.zh.service.sys.SiteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Copyright 2016 envCloud Inc.
 *
 * @author 赵梦霞
 * @description:
 */
@RestController
@RequestMapping(value = "/cockpit")
@Api(description = "驾驶舱")
public class CockpitController {

    @Resource
    CockpitService cockpitService;

    @Resource
    WaterRegimeService waterRegimeService;

    @Resource
    RainService rainService;
    @Resource
    SiteService siteService;

    @GetMapping(value = "/getFocusSite")
    @ApiOperation(value = "获取关键测点")
    public Result getFocusSite() {
        return Result.success(siteService.list(new LambdaQueryWrapper<Site>().eq(Site::getIsFocus, true).select(Site::getId, Site::getName, Site::getIsFocus)));
    }

    @GetMapping(value = "getMapInfo")
    @ApiOperation(value = "获取地图信息.")
    public Result getMapInfo() {
        return cockpitService.getMapInfo();
    }

    @GetMapping(value = "getSiteWarningDetail")
    @ApiOperation(value = "获取站点预警信息")
    public Result getSiteWarningDetail(Long siteId) {
        return cockpitService.getSiteWarningDetail(siteId);
    }

    @GetMapping(value = "getResponseTrends")
    @ApiOperation(value = "获取响应动态记录")
    public Result getResponseTrends() {
        return cockpitService.getResponseTrends();
    }

    @GetMapping(value = "getRainWaterData")
    @ApiOperation(value = "获取雨水情数据")
    public Result getRainWaterData(Long siteId, Long time) {
        return cockpitService.getRainWaterData(siteId, time);
    }

    @GetMapping(value = "getKeyPointDataTrend")
    @ApiOperation(value = "获取关键测点历史数据趋势")
    public Result getKeyPointDataTrend() {
        Instant now = Instant.now();
        Long endTime = now.toEpochMilli();
        Long beginTime = now.minus(1, ChronoUnit.DAYS).toEpochMilli();
        return cockpitService.getKeyPointDataTrend(beginTime, endTime);
    }

    @GetMapping(value = "getKeyPointDataTrendDetail")
    @ApiOperation(value = "关键测点历史趋势放大图")
    public Result getKeyPointDataTrendDetail(Long siteId, Long beginTime, Long endTime) {
        return cockpitService.getKeyPointDataTrendDetail(siteId, beginTime, endTime);
    }

    @GetMapping(value = "getSiteHistoryData")
    @ApiOperation(value = "获取站点历史数据详情")
    public Result getSiteHistoryData(Long siteId) {
        Instant now = Instant.now();
        return cockpitService.getSiteHistoryData(now.minus(2, ChronoUnit.DAYS).toEpochMilli(), now.toEpochMilli(), siteId);
    }

    @GetMapping(value = "getSituationalAwareness")
    @ApiOperation(value = "获取态势感知数据")
    public Result getSituationalAwareness() {
        return cockpitService.getSituationalAwareness();
    }

    @GetMapping(value = "alarmCenterInfo")
    @ApiOperation(value = "警情中心")
    public Result alarmCenterInfo(Integer type) {
        return cockpitService.alarmCenterInfo(type);
    }

    @GetMapping(value = "getNewWarningDetail")
    @ApiOperation(value = "获取新预警详情")
    public Result getNewWarningDetail(Long siteId, Long startTime, Long finisiTime) {
        Instant now = Instant.now();
        Long endTime = now.toEpochMilli();
        Long beginTime = now.minus(2, ChronoUnit.DAYS).toEpochMilli();
        QueryDTO queryDTO = new QueryDTO();
        queryDTO.setEnd(endTime);
        queryDTO.setId(siteId);
        queryDTO.setStart(beginTime);
        queryDTO.setTime(beginTime);
        List<WaterListDTO> section = waterRegimeService.section(queryDTO);
        queryDTO.setStart(startTime == null ? beginTime : startTime);
        queryDTO.setEnd(finisiTime == null ? endTime : finisiTime);
        List<RainListDTO> polyline = rainService.polyline(queryDTO);
        return Result.success(new NewWarningDataDTO(section, polyline));
    }

    @GetMapping(value = "getInnerWarningDetail")
    @ApiOperation(value = "获取内部预警详情")
    public Result getInnerWarningDetail(Long siteId) {
        return cockpitService.getInnerWarningDetail(siteId);
    }
}
