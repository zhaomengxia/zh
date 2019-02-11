package com.zh.dto.warning;

import io.swagger.annotations.ApiModel;
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
@ApiModel
public class WarningRecordDTO implements Serializable {

    /**
     * 预警记录id.
     */
    @ApiModelProperty(value = "预警记录id")
    private Long id;
    @ApiModelProperty(value = "灾情记录id")
    private Long disaterRecordId;

    @ApiModelProperty(value = "反馈id")
    private Long feedbackRecordId;
    /**
     * 预警名称.
     */
    @ApiModelProperty(value = "预警名称")
    private String name;

    /**
     * 行政区域编码.
     */
    @ApiModelProperty(value = "行政区域编码")
    private String areaCode;

    /**
     * 行政区域名称.
     */
    @ApiModelProperty(value = "行政区域名称")
    private String areaName;

    /**
     * 预警状态.
     */
    @ApiModelProperty(value = "预警状态")
    private Integer  status;

    /**
     * 预警状态名称.
     */
    @ApiModelProperty(value = "预警状态名称")
    private String warningStatus;

    @ApiModelProperty(value = "正常值")
    private String normalValue;

    @ApiModelProperty(value = "报警值")
    private String alarmValue;
    @ApiModelProperty(value = "预警因子")
    private String factorName;
    @ApiModelProperty(value = "预警因子单位")
    private String factorUnit;
    /**
     * 预警等级id.
     */
    @ApiModelProperty(value = "预警等级id")
    private Long warningDegreeId;

    /**
     * 预警等级名称.
     */
    @ApiModelProperty(value = "预警等级名称")
    private String warningDegreeName;

    /**
     * 预警时间.
     */
    @ApiModelProperty(value = "预警时间")
    private Long warningTime;

    /**
     * 相关站点id.
     */
    @ApiModelProperty(value = "相关站点id")
    private Long siteId;

    @ApiModelProperty(value = "因子编码")
    private String factorCode;
    /**
     * 相关站点名称.
     */
    @ApiModelProperty(value = "相关站点名称")
    private String siteName;


    @ApiModelProperty(value = "预警通知")
    private String warningContent;


    @ApiModelProperty(value = "预警图标路径")
    private String icon;

    @ApiModelProperty(value = "经度")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    private String latitude;

    @ApiModelProperty(value = "泵站实景图地址")
    private String photo;

    @ApiModelProperty(value = "跨的步数")
    private Integer jump;
    @ApiModelProperty(value = "预警跨步标志")
    private Integer responseJump;
}
