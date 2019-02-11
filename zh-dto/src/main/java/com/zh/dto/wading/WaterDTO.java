package com.zh.dto.wading;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Author: Zhao MengXia
 * @Date: 2018/12/25 9:24
 */
@Data
public class WaterDTO {

    private Long id;
    @Excel(name = "序号", width = 20)
    private Integer serialNumber;

    @ApiModelProperty(value = "所属行政区名称")
    @Excel(name = "所属行政区名称")
    private String areaName;

    @ApiModelProperty(value = "行政区代码")
    @Excel(name = "行政区代码")
    private String areaCode;


    @ApiModelProperty(value = "水库名称")
    @Excel(name = "水库名称")
    private String waterName;

    @ApiModelProperty(value = "水库代码")
    @Excel(name = "水库代码")
    private String waterCode;

    @ApiModelProperty(value = "水库容量")
    @Excel(name = "水库容量")
    private String amount;


}
