package com.zh.dto.data;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright 2016 envCloud Inc.
 *
 * @author 赵梦霞
 * @description:
 */
@Data
public class KeyPointDataDTO implements Serializable {

    /**
     * 监测点id.
     */
    @ApiModelProperty(value = "监测点id")
    private Long siteId;

    /**
     * 监测点名称.
     */
    @ApiModelProperty(value = "监测点名称")
    private String siteName;

    /**
     * 上游水位数据.
     */
    @ApiModelProperty(value = "上游水位数据")
    private List<SiteRealTimeDataDTO> upstreamData;

    /**
     * 下游水位数据.
     */
    @ApiModelProperty(value = "下游水位数据")
    private List<SiteRealTimeDataDTO> downstreamData;

    /**
     * 雨量数据.
     */
    @ApiModelProperty(value = "雨量数据")
    private List<SiteRealTimeDataDTO> rainData;
}
