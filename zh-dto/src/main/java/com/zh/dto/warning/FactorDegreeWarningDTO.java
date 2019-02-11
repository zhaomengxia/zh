package com.zh.dto.warning;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * Copyright 2016 envCloud Inc.
 *
 * @author 赵梦霞
 * @description:
 */
@Data
public class FactorDegreeWarningDTO implements Serializable {

    /**
     * 规则id.
     */
    private Long ruleId;

    /**
     * 预警等级id.
     */
    @NotNull(message = "预警等级不能为空!")
    private Long warningDegreeId;

    /**
     * 预警等级名称.
     */
    private String warningDegreeName;

    /**
     * 预警规则.
     */
    List<WarningDetailDTO> warningDetailDTOS;
}
