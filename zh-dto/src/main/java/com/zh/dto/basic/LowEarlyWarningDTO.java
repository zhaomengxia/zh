package com.zh.dto.basic;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

/**
 * Copyright 2016 envCloud Inc.
 *
 * @author 赵梦霞
 * @description:
 */
@Data
public class LowEarlyWarningDTO implements Serializable {

    private Long id;

    /**
     * 序号.
     */
    @Excel(name = "序号", width = 20)
    private Integer index;

    /**
     * 行政区域名称.
     */
    @Excel(name = "行政区划名称")
    private String areaName;

    @Excel(name = "行政区划代码", width = 20)
    private String areaCode;

    @Excel(name = "预警指标时段（小时）", width = 20)
    private String earlyWarningTime;

    @Excel(name = "预警雨量（mm）", width = 20)
    private String earlyWarningRain;
}
