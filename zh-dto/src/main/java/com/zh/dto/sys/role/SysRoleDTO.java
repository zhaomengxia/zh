package com.zh.dto.sys.role;


import lombok.Data;

import java.util.List;

/**
 * @Author: Zhao MengXia
 * @Date: 2018/12/20 10:47
 */
@Data
public class SysRoleDTO {

    private Long id;


    private String roleName;


    private String description;


    private Long parentId;


    private Long roleType;

    private List<SysRoleDTO> children;
}
