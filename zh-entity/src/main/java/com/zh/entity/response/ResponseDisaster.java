package com.zh.entity.response;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 灾情统计表
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("response_disaster")
@ApiModel(value="ResponseDisaster对象", description="灾情统计表")
public class ResponseDisaster implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "反馈部门 部门id")
    @TableField("arganization_id")
    private Long arganizationId;

    @ApiModelProperty(value = "反馈人id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "统计开始时间")
    @TableField("start_time")
    private Long startTime;

    @ApiModelProperty(value = "统计结束时间")
    @TableField("end_time")
    private Long endTime;

    @ApiModelProperty(value = "反馈时间")
    @TableField("back_time")
    private Long backTime;

    @ApiModelProperty(value = "受灾人口")
    @TableField("affect_people")
    private Long affectPeople;

    @ApiModelProperty(value = "倒塌房屋间数")
    @TableField("affect_house")
    private Long affectHouse;

    @ApiModelProperty(value = "死亡人口")
    @TableField("die_people")
    private Long diePeople;

    @ApiModelProperty(value = "失踪人口")
    @TableField("miss_people")
    private Long missPeople;

    @ApiModelProperty(value = "转移人口")
    @TableField("transfer_people")
    private Long transferPeople;

    @ApiModelProperty(value = "直接经济损失（万元）")
    @TableField("economic_losses")
    private String economicLosses;

    @ApiModelProperty(value = "响应记录id")
    @TableField("warn_record_id")
    private Long warnRecordId;

    @ApiModelProperty(value = "是否已反馈")
    @TableField("is_feedback")
    private Boolean feedback;

    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Boolean deleted;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private LocalDateTime updateTime;


}
