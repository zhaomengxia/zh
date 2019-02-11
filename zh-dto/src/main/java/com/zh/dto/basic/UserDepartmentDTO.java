package com.zh.dto.basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/1/4 13:52
 */
@Data
@ApiModel
public class UserDepartmentDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户姓名")
    private String userName;

    @ApiModelProperty(value = "防汛部门id")
    @NotNull(message = "防汛部门id为必填项")
    private Long organizationId;

    @ApiModelProperty(value = "职务")
    private String position;

    @ApiModelProperty(value = "是否为责任人")
    private Boolean responsible;
}
