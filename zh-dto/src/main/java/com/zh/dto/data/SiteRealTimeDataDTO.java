package com.zh.dto.data;

import lombok.Data;

import java.io.Serializable;

/**
 * Copyright 2016 envCloud Inc.
 *
 * @author 赵梦霞
 * @description:
 */
@Data
public class SiteRealTimeDataDTO implements Serializable {

    /**
     * 泵站id.
     */
    private Long siteId;

    /**
     * 因子编码.
     */
    private String factorCode;

    /**
     * 数据时间.
     */
    private Long valueTime;

    /**
     * 因子数据.
     */
    private Double dataValue;

    /**
     * 因子id.
     */
    private Long factorId;
}
