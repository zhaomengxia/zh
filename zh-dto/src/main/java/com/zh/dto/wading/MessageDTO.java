package com.zh.dto.wading;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Zhao MengXia
 * @Date: 2018/12/24 15:11
 */
@Data
@ApiModel
public class MessageDTO {

    @ApiModelProperty(value = "名称，模糊匹配")
    private String name;
    @ApiModelProperty(value = "code，模糊匹配")
    private String code;
    @ApiModelProperty(value = "行政区划名称，模糊匹配")
    private String areaName;
    @ApiModelProperty(value = "行政区划code，模糊匹配")
    private String areaCode;

}
