package com.zh.service.data;

import com.zh.api.Result;

/**
 * Copyright 2016 envCloud Inc.
 *
 * @author 赵梦霞
 * @description:
 */

public interface CockpitService {

    /**
     * 获取地图信息.
     * @return
     */
    public Result getMapInfo();

    /**
     * 获取泵站最新预警信息.
     * @param siteId
     * @return
     */
    public Result getSiteWarningDetail(Long siteId);

    /**
     * 获取响应动态.
     * @return
     */
    public Result getResponseTrends();

    /**
     * 获取雨水情数据.
     * @param siteId
     * @param time
     * @return
     */
    public Result getRainWaterData(Long siteId, Long time);

    /**
     * 获取关键测点历史数据.
     * @return
     */
    public Result getKeyPointDataTrend(Long beginTime, Long endTime);

    /**
     * 关键测点放大图
     * @param siteId
     * @param beginTime
     * @param endTime
     * @return
     */
    public Result getKeyPointDataTrendDetail(Long siteId, Long beginTime, Long endTime);

    /**
     * 获取测点历史数据.
     * @param beginTime
     * @param endTime
     * @return
     */
    public Result getSiteHistoryData(Long beginTime, Long endTime, Long siteId);

    /**
     * 获取态势感知信息.
     * @return
     */
    public Result getSituationalAwareness();

    /**
     * 获取警情中心信息.
     * @param type
     * @return
     */
    public Result alarmCenterInfo(Integer type);

    /**
     * 获取内部预警详情(预警分析).
     * @param siteId
     * @return
     */
    public Result getInnerWarningDetail(Long siteId);
}
