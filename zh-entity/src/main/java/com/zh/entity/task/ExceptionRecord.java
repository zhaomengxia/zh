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
 * 异常记录表
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
@TableName("exception_record")
@ApiModel(value = "ExceptionRecord对象", description = "异常记录表")
public class ExceptionRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "异常点位")
    @TableField("exception_point")
    private String exceptionPoint;

    @ApiModelProperty(value = "上报时间（时间戳）")
    @TableField("report_time")
    private Long reportTime;

    @ApiModelProperty(value = "说明")
    @TableField("instruction")
    private String instruction;

    @ApiModelProperty(value = "异常状态1：新上报2：处置中3：待审核4：已完成")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "上报人id")
    @TableField("report_man")
    private Long reportMan;

    @ApiModelProperty(value = "上报人姓名")
    @TableField("report_name")
    private String reportName;

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

    @TableField(exist = false)
    @ApiModelProperty(value = "附件")
    List<ExceptionAttachment> attachments;


}
