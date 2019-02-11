package com.zh.dto.warning;

import lombok.Data;

import java.io.Serializable;

/**
 * Copyright 2016 envCloud Inc.
 *
 * @author 赵梦霞
 * @description:
 */
@Data
public class WarningRecordQueryDTO implements Serializable {

    /**
     * 预警状态.
     */
    private Integer warningStatus;

    /**
     * 行政区域编码.
     */
    private String areaCode;

    /**
     * 预警名称.
     */
    private String name;

    /**
     * 起始时间.
     */
    private Long beginTime;

    /**
     * 结束时间.
     */
    private Long endTime;
}
