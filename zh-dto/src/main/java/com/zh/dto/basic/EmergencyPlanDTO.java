package com.zh.dto.basic;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Zhao MengXia
 * @Date: 2018/12/29 9:50
 */
@Data
public class EmergencyPlanDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "上传时间")
    private String uploadTime;

    @ApiModelProperty(value = "上传人员")
    private String uploadPeople;

    @ApiModelProperty(value = "预案类型id")
    private Long planTypeId;

    @ApiModelProperty(value = "预案类型名称")
    private String planTypeName;

    @ApiModelProperty(value = "预案名称")
    private String planName;

    @ApiModelProperty(value = "所属行政区代码")
    private String areaCode;

    @ApiModelProperty(value = "所属行政区名称")
    private String areaName;

    @ApiModelProperty(value = "文件路径")
    private String filePath;

}
