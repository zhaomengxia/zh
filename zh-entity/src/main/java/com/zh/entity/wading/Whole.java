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
 * 整体情况
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("whole")
@ApiModel(value="Whole对象", description="整体情况")
public class Whole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "行政区代码")
    @TableField("area_code")
    private String areaCode;

    @ApiModelProperty(value = "堤防长度")
    @TableField("dike_length")
    private String dikeLength;

    @ApiModelProperty(value = "水库，塘坝数量")
    @TableField("water_count")
    private Long waterCount;

    @ApiModelProperty(value = "路涵数量")
    @TableField("road_count")
    private Long roadCount;

    @ApiModelProperty(value = "桥梁数量")
    @TableField("bridge_count")
    private Long bridgeCount;

    @ApiModelProperty(value = "泵站数量")
    @TableField("pump_count")
    private Long pumpCount;

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
