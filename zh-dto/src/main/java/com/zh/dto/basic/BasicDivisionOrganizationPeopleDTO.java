package com.zh.dto.basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Zhao MengXia
 * @Date: 2018/12/21 10:00
 */
@Data
@ApiModel
public class BasicDivisionOrganizationPeopleDTO {
    /**
     * 人员表 id
     */
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 人员名称
     */
    @ApiModelProperty(value = "人员名称")
    private String name;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String divisionName;
    /**
     * 是否为部门责任人
     */
    @ApiModelProperty(value = "行政区")
    private String administrativeName;
    /**
     * 职务
     */
    @ApiModelProperty(value = "职务")
    private String userPosition;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话")
    private String telephone;
    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private String gender;
}
