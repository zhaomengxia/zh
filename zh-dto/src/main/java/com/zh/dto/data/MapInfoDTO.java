package com.zh.dto.data;

import com.zh.dto.warning.WarningDegreeInfoDTO;
import com.zh.entity.warning.WarningDegree;
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
public class MapInfoDTO implements Serializable {

    /**
     * 监测站信息.
     */
    private List<SiteMapInfoDTO> siteMapInfoDTOS;

    /**
     * 预警等级图例.
     */
    private List<WarningDegree> warningDegrees;

    /**
     * 正常个数.
     */
    private Integer normalCount;

    /**
     * 离线个数.
     */
    private Integer offlineCount;
}
