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
 * 内部预警预警反馈信息表
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("warning_record_inside_message")
@ApiModel(value="WarningRecordInsideMessage对象", description="内部预警预警反馈信息表")
public class WarningRecordInsideMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "接受人id")
    @TableField("user_id")
    private Long userId;

    @TableField(exist = false)
    private String userName;

    @ApiModelProperty(value = "回复内容")
    @TableField("back_content")
    private String backContent;

    @ApiModelProperty(value = "回复时间")
    @TableField("back_time")
    private Long backTime;

    @ApiModelProperty(value = "是否回复")
    @TableField("is_back")
    private Boolean back;

    @ApiModelProperty(value = "预警记录id")
    @TableField("warn_record_id")
    private Long warnRecordId;


    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private LocalDateTime updateTime;

    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Boolean deleted;

}
