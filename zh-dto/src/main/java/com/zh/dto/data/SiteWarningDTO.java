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
public class SiteWarningDTO implements Serializable {

    /**
     * 监测点id.
     */
    @ApiModelProperty(value = "监测点id")
    private Long siteId;

    /**
     * 因子编码.
     */
    @ApiModelProperty(value = "因子编码")
    private String factorCode;

    /**
     * 因子名称.
     */
    @ApiModelProperty(value = "因子名称")
    private String factorName;

    /**
     * 数据值.
     */
    @ApiModelProperty(value = "红色的报警值")
    private Double dataValue;

    /**
     * 阈值.
     */
    @ApiModelProperty(value = "白色的阈值")
    private Double warningValue;

    /**
     * 预警等级id.
     */
    @ApiModelProperty(value = "预警等级id")
    private Long degreeId;

    /**
     * 预警等级名称.
     */
    @ApiModelProperty(value = "预警等级名称")
    private String degreeName;

    /**
     * 预警图标.
     */
    @ApiModelProperty(value = "预警图标")
    private String degreeIcon;

    /**
     * 预警时间.
     */
    @ApiModelProperty(value = "预警时间")
    private Long warningTime;

    /**
     * 预警详情.
     */
    @ApiModelProperty(value = "雨量报警专用，X小时报警")
    private String warningDetail;
}
