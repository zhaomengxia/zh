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
 * 响应启动消息表
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("response_action_message")
@ApiModel(value="ResponseActionMessage对象", description="响应启动消息表")
public class ResponseActionMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "响应记录id")
    @TableField("response_record_id")
    private Long responseRecordId;

    @ApiModelProperty(value = "反馈人id")
    @TableField("receiver_id")
    private Long receiverId;

    @ApiModelProperty(value = "反馈时间")
    @TableField("feedback_time")
    private Long feedbackTime;

    @ApiModelProperty(value = "反馈内容")
    @TableField("feedback_content")
    private String feedbackContent;

    @ApiModelProperty(value = "是否已反馈")
    @TableField("is_feedback")
    private Boolean feedback;

    @ApiModelProperty(value = "反馈部门id")
    @TableField("organization_id")
    private Long organizationId;


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
