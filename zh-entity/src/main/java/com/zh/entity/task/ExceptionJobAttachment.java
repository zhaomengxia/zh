package com.zh.entity.task;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 任务附件表
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
@TableName("exception_job_attachment")
@ApiModel(value = "ExceptionJobAttachment对象", description = "任务附件表")
public class ExceptionJobAttachment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "任务表主键")
    @TableField("exception_job_id")
    private Long exceptionJobId;

    @ApiModelProperty(value = "1:视频2：图片3：录音")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "附件路径")
    @TableField("path")
    private String path;

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


}
