package com.zh.dto.basic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: Zhao MengXia
 * @Date: 2018/12/19 13:53
 */
@Data
@ApiModel
public class BasicAdministrativeDivisionDTO {
    /**
     * id
     */
    @ApiModelProperty(value = "主键")
    private Long id;
    /**
     * 父节点
     */
    @ApiModelProperty(value = "上级 主键  父节点")
    private Long parentId;
    /**
     * 行政区名称
     */
    @ApiModelProperty(value = "行政区名称")
    private String name;
    /**
     * 行政区代码
     */
    @ApiModelProperty(value = "行政区代码")
    private String divisionCode;
    /**
     * 是否 低洼易涝
     */
    @ApiModelProperty(value = "是否 低洼易涝")
    private Boolean easyFlooded;
    /**
     * 是否 外洪威胁
     */
    @ApiModelProperty(value = "是否 外洪威胁")
    private Boolean floodThreat;
    /**
     * 行政级别
     */
    @ApiModelProperty(value = "行政级别id")
    private Long divisionGrade;

    @ApiModelProperty(value = "行政级别")
    private String divisionGradeName;
    /**
     * 上级  行政区名称
     */
    @ApiModelProperty(value = "上级  行政区名称")
    private String parentDivisionName;

    @ApiModelProperty(value = "总人口")
    private String totalPeoples;

    @ApiModelProperty(value = "总户数")
    private String totalFamily;

    @ApiModelProperty(value = "总房屋数")
    private String totalHouses;

    @ApiModelProperty(value = "行政级别类型")
    private Integer type;

    private List<BasicAdministrativeDivisionDTO> children;

}
