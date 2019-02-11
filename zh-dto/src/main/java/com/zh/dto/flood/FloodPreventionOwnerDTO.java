package com.zh.dto.flood;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: Zhao MengXia
 * @Date: 2019/1/4 13:52
 */
@Data
@ApiModel
public class FloodPreventionOwnerDTO {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "行政区名称")
    @Excel(name = "行政区名称", width = 20)
    private String areaName;

    @ApiModelProperty(value = "行政区代码")
    @Excel(name = "行政区代码", width = 20)
    private String areaCode;

    @ApiModelProperty(value = "单位")
    @Excel(name = "单位", width = 20)
    private String company;

    @ApiModelProperty(value = "姓名  用户表id")
    private Long userId;

    @ApiModelProperty(value = "姓名 ")
    @Excel(name = "姓名", width = 20)
    private String userName;

    @ApiModelProperty(value = "性别 ")
    @Excel(name = "性别", width = 20)
    private String gender;

    @ApiModelProperty(value = "职务")
    @Excel(name = "职务", width = 20)
    private String work;

    @ApiModelProperty(value = "联系电话")
    @Excel(name = "联系电话", width = 20)
    private String mobile;

    @ApiModelProperty(value = "固定电话")
    @Excel(name = "固定电话", width = 20)
    private String telephone;


    @ApiModelProperty(value = "备注")
    @Excel(name = "备注", width = 20)
    private String mark;

    @ApiModelProperty(value = "责任制类型Id")
    private Long typeId;

    @ApiModelProperty(value = "责任制类型名称")
    private String typeName;
}
