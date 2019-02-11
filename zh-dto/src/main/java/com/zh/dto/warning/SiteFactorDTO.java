package com.zh.dto.warning;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Copyright 2016 envCloud Inc.
 *
 * @author 赵梦霞
 * @description:
 */
@Data
public class SiteFactorDTO implements Serializable {

    /**
     * 泵站id.
     */
    private Long siteId;

    /**
     * 因子编码.
     */
    private String factorCode;

    /**
     * 因子名称.
     */
    private String factorName;

    /**
     * 规则id.
     */
    private Long ruleId;

    /**
     * 因子预警规则.
     */
    List<FactorDegreeWarningDTO> factorDegreeWarningDTOS;

    /**
     * 是否已配置
     */
    private Boolean hasAllocation;
}
