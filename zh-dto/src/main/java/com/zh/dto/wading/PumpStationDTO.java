package com.zh.dto.wading;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @Author: Zhao MengXia
 * @Date: 2018/12/24 17:54
 */
@Data
public class PumpStationDTO {

    private Long id;

    @Excel(name = "序号", width = 20)
    private Integer serialNumber;

    @ApiModelProperty(value = "行政区划名称")
    @Excel(name = "行政区划名称", width = 20)
    private String areaName;

    @ApiModelProperty(value = "行政区划编码")
    @Excel(name = "行政区划编码", width = 20)
    private String areaCode;


    @ApiModelProperty(value = "泵站名称")
    @Excel(name = "泵站名称", width = 20)
    private String name;

    @ApiModelProperty(value = "泵站编码")
    @Excel(name = "泵站编码", width = 20)
    private String code;

    @ApiModelProperty(value = "泵站类型")
    @Excel(name = "泵站类型", width = 20)
    private String type;

    @ApiModelProperty(value = "装机功率(kw)")
    @Excel(name = "装机功率(kw)", width = 20)
    private String power;

    @ApiModelProperty(value = "装机流量")
    @Excel(name = "装机流量", width = 20)
    private String flow;

    @ApiModelProperty(value = "备注")
    @Excel(name = "备注", width = 20)
    private String remark;


}
