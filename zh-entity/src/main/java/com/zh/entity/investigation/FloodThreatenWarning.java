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
 * 外洪威胁村落水位预警指示表
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
@TableName("flood_threaten_warning")
@ApiModel(value = "FloodThreatenWarning对象", description = "外洪威胁村落水位预警指示表")
public class FloodThreatenWarning implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "行政区划代码")
    @TableField("division_code")
    private String divisionCode;

    @ApiModelProperty(value = "行政区划名称")
    @TableField("division_name")
    private String divisionName;

    @ApiModelProperty(value = "外洪威胁河道名称")
    @TableField("river_name")
    private String riverName;

    @ApiModelProperty(value = "外洪威胁河道代码")
    @TableField("river_code")
    private String riverCode;

    @ApiModelProperty(value = "河道控制断面代码")
    @TableField("river_control_code")
    private String riverControlCode;

    @ApiModelProperty(value = "预警站点名称")
    @TableField("warning_site_name")
    private String warningSiteName;

    @ApiModelProperty(value = "预警站点代码")
    @TableField("warning_site_code")
    private String warningSiteCode;

    @ApiModelProperty(value = "预警方式（水位或流量）")
    @TableField("warning_style")
    private String warningStyle;

    @ApiModelProperty(value = "预警值")
    @TableField("warning_value")
    private String warningValue;

    @ApiModelProperty(value = "备注")
    @TableField("remark")
    private String remark;

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
