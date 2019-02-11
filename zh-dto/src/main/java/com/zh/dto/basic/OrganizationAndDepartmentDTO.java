package com.zh.dto.basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/1/8 15:05
 */
@Data
@ApiModel
public class OrganizationAndDepartmentDTO {
    @ApiModelProperty(value = "指挥部主键")
    private Long id;
    @ApiModelProperty(value = "指挥部名称")
    private String name;
    @ApiModelProperty(value = "指挥部下部门")
    private List<DepartmentDTO> departmentDTOS;
}
