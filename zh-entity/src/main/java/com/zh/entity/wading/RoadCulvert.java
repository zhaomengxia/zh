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
 * 路涵
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
@TableName("road_culvert")
@ApiModel(value="RoadCulvert对象", description="路涵")
public class RoadCulvert implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "行政区划编码")
    @TableField("area_code")
    private String areaCode;

    @ApiModelProperty(value = "路涵名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "路涵编码")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "涵洞高")
    @TableField("height")
    private String height;

    @ApiModelProperty(value = "涵洞宽")
    @TableField("width")
    private String width;

    @ApiModelProperty(value = "涵洞长")
    @TableField("length")
    private String length;

    @ApiModelProperty(value = "涵洞类型")
    @TableField("type")
    private String type;

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
