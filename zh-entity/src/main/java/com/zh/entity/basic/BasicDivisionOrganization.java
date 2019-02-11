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
@TableName("basic_division_organization")
@ApiModel(value="BasicDivisionOrganization对象", description="")
public class BasicDivisionOrganization implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "行政区id")
    @TableField("administrative_division_id")
    private Long administrativeDivisionId;

    @TableField(exist = false)
    @ApiModelProperty(value = "行政区")
    private String areaName;

    @ApiModelProperty(value = "防汛部门名称")
    @TableField("division_name")
    private String divisionName;

    @ApiModelProperty(value = "防汛部门职责")
    @TableField("division_duties")
    private String divisionDuties;

    @ApiModelProperty(value = "联系电话")
    @TableField("telephone")
    private String telephone;

    @ApiModelProperty(value = "责任人")
    @TableField("duty_people_name")
    private String dutyPeopleName;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "责任人职务")
    @TableField("duty_people_work")
    private String dutyPeopleWork;

    @ApiModelProperty(value = "指挥部id")
    @TableField("command_id")
    private Long commandId;

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


}
