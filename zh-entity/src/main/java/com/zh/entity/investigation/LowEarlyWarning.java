package com.zh.entity.investigation;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 低洼易涝村落雨量预警指标表
 * </p>
 *
 * @author  赵梦霞
 * @since 2019-01-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("low_early_warning")
@ApiModel(value = "LowEarlyWarning对象", description = "低洼易涝村落雨量预警指标表")
public class LowEarlyWarning implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "行政区划代码")
    @TableField("division_code")
    private String divisionCode;

    @ApiModelProperty(value = "行政区划名称")
    @TableField("division_name")
    private String divisionName;

    @ApiModelProperty(value = "预警指标时段（小时）")
    @TableField("early_warning_time")
    private String earlyWarningTime;

    @ApiModelProperty(value = "预警雨量（mm）")
    @TableField("early_warning_rain")
    private String earlyWarningRain;

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
