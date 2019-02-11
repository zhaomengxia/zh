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
public class WarningDegreeInfoDTO implements Serializable {

    /**
     * 预警等级id.
     */
    private Long degreeId;

    /**
     * 预警等级名称.
     */
    private String degreeName;

    /**
     * 预警等级图标.
     */
    private String degreeIcon;
}
