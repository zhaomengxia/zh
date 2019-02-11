package com.zh.dto.basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Zhao MengXia
 * @Date: 2018/12/28 14:34
 */
@Data
@ApiModel
public class AdministrativeIdAndCodeAndNameDTO {
    @ApiModelProperty(value = "行政区主键")
    private Long id;
    @ApiModelProperty(value = "行政区编码")
    private String areaCode;
    @ApiModelProperty(value = "所属行政区名称")
    private String areaName;
    @ApiModelProperty(value = "所属行政区级别")
    private Long grade;
}
