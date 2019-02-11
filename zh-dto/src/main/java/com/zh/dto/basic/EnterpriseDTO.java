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
public class EnterpriseDTO implements Serializable {

    /**
     * id.
     */
    private Long id;

    @Excel(name = "序号", width = 20)
    private Integer serialNumber;
    /**
     * 企事业名称.
     */
    @Excel(name = "企事业名称", width = 20)
    private String name;

    /**
     * 单位类别.
     */
    @Excel(name = "单位类别", width = 20)
    private String type;

    /**
     * 行政区划名称.
     */
    @Excel(name = "行政区划名称", width = 20)
    private String areaName;

    /**
     * 行政区划编码.
     */
    private String areaCode;

    /**
     * 地址.
     */
    @Excel(name = "地址", width = 20)
    private String address;

    /**
     * 企业性质.
     */
    @Excel(name = "企业性质", width = 20)
    private String nature;

    /**
     * 在岗人数.
     */
    @Excel(name = "在岗人数", width = 20)
    private Integer dutyNumber;

    /**
     * 近三年平均产值.
     */
    @Excel(name = "近三年平均产值", width = 20)
    private Double averageOutputValue;

    /**
     * 危化品.
     */
    @Excel(name = "危化品", width = 20)
    private String hazardousChemicals;

    /**
     * 是否低洼易涝.
     */
//    @Excel(name = "是否低洼易涝", width = 20)
    private Boolean waterlogged;

    /**
     * 是否低洼易涝.
     */
    @Excel(name = "是否低洼易涝", width = 20)
    private String waterloggs;
    /**
     * 是否外洪威胁.
     */
//    @Excel(name = "是否外洪威胁", width = 20)
    private Boolean floodThreat;

    /**
     * 是否外洪威胁.
     */
    @Excel(name = "是否外洪威胁", width = 20)
    private String floodThreats;

    /**
     * 描述.
     */
    @Excel(name = "描述", width = 20)
    private String description;
}
