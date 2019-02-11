package com.zh.dto.data;

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
public class SiteMapInfoDTO implements Serializable {

    /**
     * 监测站id.
     */
    private Long siteId;

    /**
     * 监测站编码.
     */
    private String siteCode;


    /**
     * 监测站名称.
     */
    private String siteName;

    /**
     * 纬度.
     */
    private String latitude;

    /**
     * 经度.
     */
    private String longitude;

    /**
     * 预警等级id.
     */
    private Long warningDegreeId;

    /**
     * 预警等级名称.
     */
    private String warningDegreeName;

    /**
     * 预警等级图标.
     */
    private String warningDegreeIcon;

    /**
     * 发生预警的因子数.
     */
    private Integer warningCount;

    /**
     * 监测点预警信息.
     */
    private List<SiteWarningDTO> siteWarningDTOS;
}
