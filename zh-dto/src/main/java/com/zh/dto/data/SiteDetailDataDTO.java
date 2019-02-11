package com.zh.dto.data;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Copyright 2016 envCloud Inc.
 *
 * @author 赵梦霞
 * @description:
 */
@Data
public class SiteDetailDataDTO implements Serializable {

    @ApiModelProperty(value = "站点名称")
    private String siteName;

    @ApiModelProperty(value = "站点id")
    private Long siteId;

    /**
     * 雨量实时数据.
     */
    @ApiModelProperty(value = "雨量实时数据")
    private Double rainRealData;

    /**
     * 上游水位.
     */
    @ApiModelProperty(value = "上游实时水位数据")
    private Double upstreamData;

    /**
     * 下游水位.
     */
    @ApiModelProperty(value = "下游实时水位数据")
    private Double downstreamData;

    /**
     * 不同时段雨量数据.
     */
    @ApiModelProperty(value = "不同时段雨量数据")
    private Map<String, Double> rainMap;

    @ApiModelProperty(value = "水位历史数据")
    private List<WaterListDTO> waterListDTOS;

    @ApiModelProperty(value = "雨量小时累计数据")
    private List<SiteRealTimeDataDTO> rainDatas;
}
