package com.zh.entity.warning;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 预警信息详情
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("warning_rule_detail")
@ApiModel(value = "WarningRuleDetail对象", description = "预警信息详情")
public class WarningRuleDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @TableField("rule_id")
    private Long ruleId;

    @ApiModelProperty(value = "阚值")
    @TableField("warning_value")
    private Double warningValue;

    @ApiModelProperty(value = "时间区间")
    @TableField("time_zone")
    private Integer timeZone;

    @JsonIgnore
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    private Boolean deleted;

    @JsonIgnore
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @JsonIgnore
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "预警等级")
    @TableField("warning_degree")
    private Long warningDegree;

}
