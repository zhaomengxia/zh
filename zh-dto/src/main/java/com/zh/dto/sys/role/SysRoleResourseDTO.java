package com.zh.dto.sys.role;


import lombok.Data;

import java.util.List;

/**
 * @Author: Zhao MengXia
 * @Date: 2018/12/20 16:35
 */
@Data
public class SysRoleResourseDTO {
    /**
     * id
     */
    private Long id;
    /**
     * 是否 选择该资源
     */
    private Boolean hasPersission;
    /**
     * 资源菜单名称
     */
    private String sysResourcesName;
    /**
     * 路径
     */
    private String httpPath;
    /**
     * 父节点
     */
    private Long parentId;
    /**
     * 节点
     */
    private String node;
    /**
     * 孩娃们
     */
    private List<SysRoleResourseDTO> children;
}
