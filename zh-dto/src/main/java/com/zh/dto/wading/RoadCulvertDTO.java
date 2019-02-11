package com.zh.dto.wading;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Zhao MengXia
 * @Date: 2018/12/24 19:56
 */
@Data
public class RoadCulvertDTO {

    private Long id;
    @Excel(name = "序号", width = 20)
    private Integer serialNumber;

    @ApiModelProperty(value = "所属行政区划名称")
    @Excel(name = "所属行政区划名称", width = 20)
    private String areaName;

    @ApiModelProperty(value = "行政区划编码")
    @Excel(name = "行政区划编码", width = 20)
    private String areaCode;

    @ApiModelProperty(value = "路涵名称")
    @Excel(name = "路涵名称", width = 20)
    private String name;

    @ApiModelProperty(value = "路涵编码")
    @Excel(name = "路涵编码", width = 20)
    private String code;

    @ApiModelProperty(value = "涵洞类型")
    @Excel(name = "涵洞类型", width = 20)
    private String type;

    @ApiModelProperty(value = "涵洞高")
    @Excel(name = "涵洞高（m）", width = 20)
    private String height;

    @ApiModelProperty(value = "涵洞长")
    @Excel(name = "涵洞长（m）", width = 20)
    private String length;

    @ApiModelProperty(value = "涵洞宽")
    @Excel(name = "涵洞宽（m）", width = 20)
    private String width;

}
