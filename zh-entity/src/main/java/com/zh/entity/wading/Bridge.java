package com.zh.entity.wading;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 桥梁
 * </p>
 *
 * @author 赵梦霞
 * @since 2018-12-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("bridge")
@ApiModel(value="Bridge对象", description="桥梁")
public class Bridge implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "行政区划编码")
    @TableField("area_code")
    private String areaCode;

    @ApiModelProperty(value = "桥梁名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "桥梁编码")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "桥梁类型")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "桥梁高度")
    @TableField("height")
    private String height;

    @ApiModelProperty(value = "桥梁宽度")
    @TableField("width")
    private String width;

    @ApiModelProperty(value = "桥梁长度")
    @TableField("length")
    private String length;

    @JsonIgnore
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(hidden = true)
    private LocalDateTime createTime;

    @JsonIgnore
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(hidden = true)
    private LocalDateTime updateTime;

    @JsonIgnore
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    @TableLogic
    @ApiModelProperty(hidden = true)
    private Boolean deleted;
}
