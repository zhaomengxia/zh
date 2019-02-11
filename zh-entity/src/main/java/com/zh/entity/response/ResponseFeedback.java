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
 * 响应反馈
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
@TableName("response_feedback")
@ApiModel(value="ResponseFeedback对象", description="响应反馈")
public class ResponseFeedback implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "响应记录id")
    @TableField("warn_record_id")
    private Long warnRecordId;

    @ApiModelProperty(value = "反馈人")
    @TableField("back_people")
    private String backPeople;

    @ApiModelProperty(value = "反馈部门 部门id")
    @TableField("organization_id")
    private Long organizationId;

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

    @ApiModelProperty(value = "投入抢险人员")
    @TableField("rescue_people")
    private String rescuePeople;

    @ApiModelProperty(value = "需转移群众")
    @TableField("need_transfer_people")
    private String needTransferPeople;

    @ApiModelProperty(value = "已转移群众")
    @TableField("transferred_people")
    private String transferredPeople;

    @ApiModelProperty(value = "受围困群众")
    @TableField("besieged_people")
    private String besiegedPeople;

    @ApiModelProperty(value = "已转移围困群众")
    @TableField("not_besieged_people")
    private String notBesiegedPeople;

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
