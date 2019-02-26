package com.zh.dto.user;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author hahaha
 * @description:
 */
@Data
public class RoleResourceDTO implements Serializable {

    /**
     * id
     */
    private Long id;

    /**
     * 名称.
     */
    private String name;

    /**
     * 描述.
     */
    private String description;

    /**
     * 访问路径.
     */
    private String httpPath;


    /**
     * 父类id.
     */
    private Long parentId;

    /**
     * 是否拥有权限.
     */
    private Boolean hasPermission;

    /**
     * url.
     */
    private String url;

    /**
     * 节点.
     */
    private String node;

    /**
     * 顺序.
     */
    private Integer seq;

    /**
     * 图标.
     */
    private String icon;

    /**
     * 子类权限.
     */
    private List<RoleResourceDTO> roleResourceDTOS;
}
