package com.zh.entity.basic;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("basic_administrative_division")
@ApiModel(value = "BasicAdministrativeDivision对象", description = "")
public class BasicAdministrativeDivision implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "上级行政区")
    @TableField("parent_id")
    private Long parentId;

    @ApiModelProperty(value = "行政区名称")
    @TableField("division_name")
    private String divisionName;

    @ApiModelProperty(value = "行政区代码")
    @TableField("division_code")
    private String divisionCode;

    @ApiModelProperty(value = "是否 低洼易涝")
    @TableField("is_easy_flooded")
    private Boolean easyFlooded;

    @ApiModelProperty(value = "是否 外洪威胁")
    @TableField("is_flood_threat")
    private Boolean floodThreat;

    @ApiModelProperty(value = "行政级别id")
    @TableField("division_grade")
    private Long divisionGrade;

    @ApiModelProperty(value = "上级行政区名称")
    @TableField("parent_division_name")
    private String parentDivisionName;

    @JsonIgnore
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    @ApiModelProperty(hidden = true)
    private Boolean deleted;

    @JsonIgnore
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(hidden = true)
    private LocalDateTime createTime;

    @JsonIgnore
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(hidden = true)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "总人口")
    @TableField(value = "total_peoples")
    private String totalPeoples;

    @ApiModelProperty(value = "总户数")
    @TableField(value = "total_family")
    private String totalFamily;

    @ApiModelProperty(value = "总房屋数")
    @TableField(value = "total_houses")
    private String totalHouses;

    @ApiModelProperty(value = "行政级别类型")
    @TableField(value = "type")
    private Integer type;

}
