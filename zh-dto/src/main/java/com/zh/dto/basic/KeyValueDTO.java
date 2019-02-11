package com.zh.dto.basic;

import lombok.Data;

import java.io.Serializable;

/**
 * Copyright 2016 envCloud Inc.
 *
 * @author 赵梦霞
 * @description:
 */
@Data
public class KeyValueDTO implements Serializable {

    private Long id;

    private String name;

    private String path;
}
