package com.zh.dto.warning;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/1/15 13:39
 */
@Data
@ApiModel
public class DepartmentUserDTO {
    @ApiModelProperty(value = "指挥部部门id")
    private Long organizationId;
    @ApiModelProperty(value = "指挥部部门名称")
    private String organizationName;
    @ApiModelProperty(value = "指挥部部门成员id")
    private Long userId;
    @ApiModelProperty(value = "指挥部部门成员姓名")
    private String userName;

    /**
     * 部门成员列表.
     */
    private List<DepartmentUserDTO> list;
}
