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
public class SituationalAwarenessDTO implements Serializable {

    /**
     * 降雨强度.
     */
    private String rain;

    /**
     * 降雨小时增量.
     */
    private String rainRate;

    /**
     * 水位小时增量.
     */
    private String levelRate;

    /**
     * 水位风险指数.
     */
    private String levelRisk;
}
