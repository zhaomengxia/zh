package com.zh.entity.basic;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 应急 预案类型表
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("emergency_plan_type")
@ApiModel(value="EmergencyPlanType对象", description="应急 预案类型表")
public class EmergencyPlanType implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableField("id")
    private Long id;

    @ApiModelProperty(value = "预案类型id")
    @TableField("name")
    private String name;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private LocalDateTime updateTime;

    @JsonIgnore
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    @ApiModelProperty(hidden = true)
    private Boolean deleted;


}
