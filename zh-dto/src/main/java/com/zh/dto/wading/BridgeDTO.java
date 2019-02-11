package com.zh.dto.wading;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Author: Zhao MengXia
 * @Date: 2018/12/24 15:57
 */
@Data
public class BridgeDTO {

    @ApiModelProperty(value = "桥梁主键")
    private Long id;

    @Excel(name = "序号", width = 20)
    private Integer serialNumber;

    @ApiModelProperty(value = "所属行政区划名称")
    @Excel(name = "所属行政区划名称", width = 20)
    private String areaName;

    @ApiModelProperty(value = "行政区划编码")
    @Excel(name = "行政区划编码", width = 20)
    private String areaCode;


    @ApiModelProperty(value = "桥梁名称")
    @Excel(name = "桥梁名称", width = 20)
    private String bridgeName;

    @ApiModelProperty(value = "桥梁编码")
    @Excel(name = "桥梁编码", width = 20)
    private String code;

    @ApiModelProperty(value = "桥梁类型")
    @Excel(name = "桥梁类型", width = 20)
    private String type;

    @ApiModelProperty(value = "桥梁长度")
    @Excel(name = "桥梁长度(m)", width = 20)
    private String length;

    @ApiModelProperty(value = "桥梁高度")
    @Excel(name = "桥梁高度(m)", width = 20)
    private String height;

    @ApiModelProperty(value = "桥梁宽度")
    @Excel(name = "桥梁宽度(m)", width = 20)
    private String width;

}
