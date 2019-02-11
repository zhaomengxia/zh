package com.zh.entity.task;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 异常任务表
 * </p>
 *
 * @author  赵梦霞
 * @since 2019-01-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("exception_job")
@ApiModel(value = "ExceptionJob对象", description = "异常任务表")
public class ExceptionJob implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "异常主键")
    @TableField("exception_id")
    private Long exceptionId;

    @ApiModelProperty(value = "任务标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "任务说明")
    @TableField("instruction")
    private String instruction;

    @ApiModelProperty(value = "指派人id")
    @TableField("assign_man")
    private Long assignMan;

    @ApiModelProperty(value = "指派人姓名")
    @TableField("assign_name")
    private String assignName;

    @ApiModelProperty(value = "指派时间")
    @TableField("assign_time")
    private Long assignTime;

    @ApiModelProperty(value = "执行人id")
    @TableField("execute_man")
    private Long executeMan;

    @ApiModelProperty(value = "执行人名称")
    @TableField("execute_name")
    private String executeName;

    @ApiModelProperty(value = "截止日")
    @TableField("deadline")
    private Long deadline;

    @ApiModelProperty(value = "状态：1：待办任务2：已办任务")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "反馈说明")
    @TableField("feedback_desc")
    private String feedbackDesc;

    @ApiModelProperty(value = "完成时间")
    @TableField("finish_time")
    private Long finishTime;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    private Boolean deleted;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @JsonIgnore
    @ApiModelProperty(hidden = true)
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "反馈任务附件")
    @TableField(exist = false)
    private List<ExceptionJobAttachment> attachments;
}
