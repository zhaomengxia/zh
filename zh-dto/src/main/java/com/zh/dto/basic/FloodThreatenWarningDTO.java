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
public class FloodThreatenWarningDTO implements Serializable {

    private Long id;

    @Excel(name = "序号", width = 20)
    private Integer index;

    @Excel(name = "行政区划编码", width = 20)
    private String divisionCode;

    @Excel(name = "行政区划名称", width = 20)
    private String divisionName;

    @Excel(name = "外洪威胁河道名称", width = 20)
    private String riverName;

    @Excel(name = "外洪威胁河道代码", width = 20)
    private String riverCode;

    @Excel(name = "河道控制断面代码", width = 20)
    private String riverControlCode;

    @Excel(name = "预警站点名称", width = 20)
    private String warningSiteName;

    @Excel(name = "预警站点代码", width = 20)
    private String warningSiteCode;

    @Excel(name = "预警方式（水位或流量）", width = 20)
    private String warningStyle;

    @Excel(name = "预警值", width = 20)
    private String warningValue;

    @Excel(name = "备注", width = 20)
    private String remark;
}
