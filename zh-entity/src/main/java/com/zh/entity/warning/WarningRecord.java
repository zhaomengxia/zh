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
 * 预警记录表
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("warning_record")
@ApiModel(value = "WarningRecord对象", description = "预警记录表")
public class WarningRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "预警名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "预警状态")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "预警等级")
    @TableField("warning_degree")
    private Long warningDegree;

    @ApiModelProperty(value = "预警时间")
    @TableField("warning_time")
    private Long warningTime;

    @ApiModelProperty(value = "预警测点")
    @TableField("site_id")
    private Long siteId;

    @ApiModelProperty(value = "预警通知")
    @TableField("warning_content")
    private String warningContent;

    @ApiModelProperty(value = "因子编码")
    @TableField("factor_code")
    private String factorCode;

    @ApiModelProperty(value = "跳步 标志")
    @TableField("jump")
    private Integer jump;

    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
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
