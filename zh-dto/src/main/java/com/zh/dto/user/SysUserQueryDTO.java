package com.zh.dto.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户查询条件封装
 *
 * @author hahaha
 * @since 2018-12-19 13:43
 **/
@Data
@ApiModel
public class SysUserQueryDTO {

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "用户角色id")
    private Long sysRoleId;

}
