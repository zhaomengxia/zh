package com.zh.dto.sys;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Copyright 2016 envCloud Inc.
 *
 * @author 赵梦霞
 * @description:
 */
@Data
public class MenuInfoDTO implements Serializable {

    /**
     * 菜单id.
     */
    private Long menuId;

    /**
     * 菜单名称.
     */
    @NotNull(message = "菜单名称不可为空!")
    private String menuName;

    /**
     * 父类id.
     */
    private Long parentId;

    /**
     * 菜单层级.
     */
    @NotNull(message = "菜单层级不可为空!")
    private Integer layer;

    /**
     * 路由.
     */
    @NotBlank(message = "菜单路径不可为空!")
    private String httpPath;

    /**
     * 节点.
     */
    private String node;

    /**
     * 显示顺序.
     */
    @NotNull(message = "显示顺序不可为空!")
    private Integer seq;

    /**
     * 菜单描述.
     */
    private String description;
}
