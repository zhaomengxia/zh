package com.zh.dto.basic;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Zhao MengXia
 * @Date: 2018/12/19 15:44
 */
@Data
@ApiModel
public class BasicDivisionOrganizationDTO {
    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "行政区id")

    private Long administrativeDivisionId;

    @ApiModelProperty(value = "行政区")
    @Excel(name = "行政区划", width = 20)
    private String areaName;

    @ApiModelProperty(value = "防汛部门名称")
    @Excel(name = "部门名称", width = 20)
    private String divisionName;

    @ApiModelProperty(value = "防汛部门职责")
    @Excel(name = "部门职责", width = 20)
    private String divisionDuties;

    @ApiModelProperty(value = "责任人")
    @Excel(name = "责任人", width = 20)
    private String dutyPeopleName;

    @ApiModelProperty(value = "责任人职务")
    @Excel(name = "职务", width = 20)
    private String dutyPeopleWork;

    @ApiModelProperty(value = "联系方式")
    @Excel(name = "联系电话", width = 20)
    private String telephone;

    @ApiModelProperty(value = "行政区代码")
    private String areaCode;

    @ApiModelProperty(value = "指挥部id")
    private Long commandId;

}
