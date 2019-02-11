package com.zh.mapper.data;

import com.zh.dto.data.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Copyright 2016 envCloud Inc.
 *
 * @author 赵梦霞
 * @description:
 */

public interface CockpitMapper {

    List<SiteMapInfoDTO> getSiteMapInfo();

    List<SiteWarningDTO> getNewestSiteWarning(@Param("siteId") Long siteId);

    List<SiteRealTimeDataDTO> getSiteFactorRealTimeData();

    List<SiteFactorDTO> getSiteFactorInfo();

    List<SiteRealTimeDataDTO> getAvgHourData(@Param("beginTime") Long beginTime, @Param("endTime") Long endTime);

    List<SiteRealTimeDataDTO> getSumHourData(@Param("beginTime") Long beginTime, @Param("endTime") Long endTime);

    List<SiteRealTimeDataDTO> getAvgDayData(@Param("beginTime") Long beginTime, @Param("endTime") Long endTime);

    List<SiteRealTimeDataDTO> getSumDayData(@Param("beginTime") Long beginTime, @Param("endTime") Long endTime);

    /**
     * 查询关键监测点水位每隔十分钟的分钟历史数据.
     *
     * @param beginTime 开始时间.
     * @param endTime   结束时间.
     * @param factorId  上下游因子id, 1:上游水位 2:下游水位.
     * @return
     */
    List<SiteRealTimeDataDTO> getLevelTimeData(@Param("beginTime") Long beginTime,
                                               @Param("endTime") Long endTime,
                                               @Param("factorId") Long factorId);

    List<SiteRealTimeDataDTO> getLevelTimeDataDetail(@Param("beginTime") Long beginTime,
                                                     @Param("endTime") Long endTime,
                                                     @Param("factorId") Long factorId,
                                                     @Param("siteId") Long siteId);

    /**
     * 查询关键监测点雨量小时历史数据.
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    List<SiteRealTimeDataDTO> getRainHourData(@Param("beginTime") Long beginTime,
                                              @Param("endTime") Long endTime,
                                              @Param("siteId")Long siteId);

    /**
     * 降雨小时平均值.
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    Double getAvgRainHourData(@Param("beginTime") Long beginTime,
                              @Param("endTime") Long endTime);

    /**
     * 获取重点雨水监测站列表.
     *
     * @return
     */
    List<KeyPointDataDTO> getKeySiteList(@Param("siteId") Long siteId);

    /**
     * 获取预警分析内容.
     *
     * @param siteId
     * @return
     */
    AreaDetailDTO getSiteArea(@Param("siteId") Long siteId);
}
