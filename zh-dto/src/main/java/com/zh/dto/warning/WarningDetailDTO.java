package com.zh.dto.warning;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Copyright 2016 envCloud Inc.
 *
 * @author 赵梦霞
 * @description:
 */
@Data
public class WarningDetailDTO implements Serializable {

    /**
     * 时间区间.
     */
    private Integer timeZone;

    /**
     * 预警值.
     */
    @NotNull(message = "预警值不能为空!")
    private Double warningValue;

    /**
     * 预警等级id.
     */
    private Long warningDegreeId;

    /**
     * 预警等级名称.
     */
    private String warningDegreeName;

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
     * 规则详情id.
     */
    private Long detailId;

    /**
     * 逻辑删除字段.
     */
    private Boolean deleted;
}
