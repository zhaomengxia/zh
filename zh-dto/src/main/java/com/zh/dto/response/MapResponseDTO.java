package com.zh.dto.response;

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
public class MapResponseDTO implements Serializable {

    /**
     * 预警记录id.
     */
    @ApiModelProperty(value = "预警记录id")
    private Long warningRecordId;

    /**
     * 预警监测站名称.
     */
    @ApiModelProperty(value = "预警监测站名称")
    private String siteName;

    /**
     * 所属区域名称.
     */
    @ApiModelProperty(value = "所属区域名称")
    private String areaName;

    /**
     * 响应状态.
     */
    @ApiModelProperty(value = "响应状态")
    private Integer responseStatus;

    /**
     * 预警等级.
     */
    @ApiModelProperty(value = "预警等级")
    private String degreeName;

    /**
     * 响应内容.
     */
    @ApiModelProperty(value = "响应内容")
    private String responseContent;

    /**
     * 反馈信息记录.
     */
    @ApiModelProperty(value = "反馈信息记录")
    List<ReponseActionMessageDTO> reponseActionMessageDTOS;
}
