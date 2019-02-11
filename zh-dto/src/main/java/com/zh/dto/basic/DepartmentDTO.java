package com.zh.dto.basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/1/9 9:36
 */
@Data
@ApiModel
public class DepartmentDTO {
    @ApiModelProperty(value = "部门主键")
    private Long id;
    @ApiModelProperty(value = "部门名称")
    private String name;

    private List<UserDTO> userDTOS;
}
