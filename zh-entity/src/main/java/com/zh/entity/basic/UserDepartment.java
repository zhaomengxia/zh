package com.zh.entity.basic;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 人员部门表
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("user_department")
@ApiModel(value = "UserDepartment对象", description = "人员部门表")
public class UserDepartment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "用户id")
    @TableField("user_id")
    @NotNull(message = "用户id为必填项")
    private Long userId;

    @ApiModelProperty(value = "防汛部门id")
    @TableField("organization_id")
    @NotNull(message = "防汛部门id为必填项")
    private Long organizationId;

    @ApiModelProperty(value = "职务")
    @TableField("position")
    private String position;

    @ApiModelProperty(value = "是否为责任人")
    @TableField("is_responsible")
    private Boolean responsible;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private LocalDateTime updateTime;

    @TableField(value = "is_deleted", fill = FieldFill.INSERT_UPDATE)
    @TableLogic
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private Boolean deleted;


}
