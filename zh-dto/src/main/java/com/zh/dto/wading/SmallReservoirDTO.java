package com.zh.dto.wading;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Zhao MengXia
 * @Date: 2018/12/24 15:03
 */
@Data
public class SmallReservoirDTO {

    private Long id;
    @Excel(name = "序号", width = 20)
    private Integer serialNumber;

    @ApiModelProperty(value = "所属行政区名称")
    @Excel(name = "所属行政区名称", width = 20)
    private String areaName;

    @ApiModelProperty(value = "所属行政区代码")
    @Excel(name = "所属行政区代码", width = 20)
    private String areaCode;

    @ApiModelProperty(value = "名称")
    @Excel(name = "名称", width = 20)
    private String name;
    @ApiModelProperty(value = "塘坝编码")
    @Excel(name = "塘坝代码", width = 20)
    private String code;

    @ApiModelProperty(value = "容量")
    @Excel(name = "蓄水量(m3)", width = 20)
    private String capacity;

    @ApiModelProperty(value = "坝高")
    @Excel(name = "坝高", width = 20)
    private String height;

    @ApiModelProperty(value = "坝长")
    @Excel(name = "坝长", width = 20)
    private String length;

    @ApiModelProperty(value = "挡水坝主要类型")
    @Excel(name = "挡水坝主要类型", width = 20)
    private String type;

}
