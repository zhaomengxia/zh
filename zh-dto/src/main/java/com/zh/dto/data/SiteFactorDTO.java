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
public class SiteFactorDTO implements Serializable {

    /**
     * 监测点id.
     */
    private Long siteId;

    /**
     * 因子id.
     */
    private Long factorId;

    /**
     * 因子编码.
     */
    private String factorCode;

    /**
     * 设备id.
     */
    private Long deviceId;
}
