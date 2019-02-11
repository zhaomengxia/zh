package com.zh.dto.data;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Copyright 2016 envCloud Inc.
 *
 * @author 赵梦霞
 * @description:
 */
@Data
public class AreaDetailDTO implements Serializable {

    /**
     * 纬度.
     */
    @ApiModelProperty(value = "纬度")
    private String latitude;

    /**
     * 经度.
     */
    @ApiModelProperty(value = "经度")
    private String longitude;

    /**
     * 区域名称.
     */
    @ApiModelProperty(value = "区域名称")
    private String areaName;

    /**
     * 总人口.
     */
    @ApiModelProperty(value = "总人口")
    private String totalPopulation;

    /**
     * 总户数.
     */
    @ApiModelProperty(value = "总户数")
    private String totalFamily;
}
