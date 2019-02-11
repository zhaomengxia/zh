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
public class WarningPublishDTO implements Serializable {

    /**
     * 预警id.
     */
    private Long id;

    /**
     * 预警发布内容.
     */
    private String warningContent;

    /**
     *
     */
    private String headQuarters;
}
