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
 * 预警等级表
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("warning_degree")
@ApiModel(value="WarningDegree对象", description="预警等级表")
public class WarningDegree implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "预警名称")
    @TableField("name")
    private String name;

    @JsonIgnore
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    @ApiModelProperty(hidden = true)
    private Boolean deleted;

    @JsonIgnore
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(hidden = true)
    private LocalDateTime createTime;

    @JsonIgnore
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(hidden = true)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "预警图标")
    @TableField("icon")
    private String icon;


}
