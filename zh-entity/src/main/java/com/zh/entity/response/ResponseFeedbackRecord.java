package com.zh.entity.response;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 响应反馈记录表
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("response_feedback_record")
@ApiModel(value="ResponseFeedbackRecord对象", description="响应反馈记录表")
public class ResponseFeedbackRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "响应记录id")
    @TableField("response_record_id")
    private Long responseRecordId;

    @ApiModelProperty(value = "反馈通知")
    @TableField("feed_back_message")
    private String feedBackMessage;

    @ApiModelProperty(value = "防汛责任人")
    @TableField("head_quarters")
    private String headQuarters;

    @ApiModelProperty(value = "响应部门（防汛指挥部）")
    @TableField("department")
    private String department;

    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    private Boolean deleted;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
