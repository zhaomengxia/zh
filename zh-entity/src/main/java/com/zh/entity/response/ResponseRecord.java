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
 * 预警响应记录表
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
@TableName("response_record")
@ApiModel(value="ResponseRecord对象", description="预警响应记录表")
public class ResponseRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "预警记录id")
    @TableField("warning_record_id")
    private Long warningRecordId;

    @ApiModelProperty(value = "响应等级")
    @TableField("response_degree")
    private Integer responseDegree;

    @ApiModelProperty(value = "响应内容")
    @TableField("response_content")
    private String responseContent;

    @ApiModelProperty(value = "响应状态 1:已响应启动 2:响应反馈中 3:灾情统计中 4：关闭响应 5：关闭预警")
    @TableField("response_status")
    private Integer responseStatus;

    @ApiModelProperty(value = "防汛负责人")
    @TableField("charge_man")
    private String chargeMan;

    @ApiModelProperty(value = "防汛部门")
    @TableField("department")
    private String department;

    @ApiModelProperty(value = "响应启动时间")
    @TableField("response_time")
    private Long responseTime;

    @ApiModelProperty(value = "跳步 标志")
    @TableField("jump")
    private Integer jump;

    @ApiModelProperty(value = "是否恢复正常")
    @TableField("is_normal")
    private Boolean normal;

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

    @Version
    @TableField(value = "version")
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Integer version;

}
