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
 * 堤防
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
@TableName("dike")
@ApiModel(value="Dike对象", description="堤防")
public class Dike implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "堤防名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "堤防编码")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "行政区域编码")
    @TableField("area_code")
    private String areaCode;

    @ApiModelProperty(value = "所在河流(湖泊、海岸)")
    @TableField("river")
    private String river;

    @ApiModelProperty(value = "河流类型")
    @TableField("river_type")
    private String riverType;

    @ApiModelProperty(value = "堤坝跨界情况")
    @TableField("transboundary")
    private String transboundary;

    @ApiModelProperty(value = "堤防类型")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "堤防形式")
    @TableField("form")
    private String form;

    @ApiModelProperty(value = "堤防级别")
    @TableField("degree")
    private String degree;

    @ApiModelProperty(value = "设计防洪(潮)标准［重现期(年)")
    @TableField("recurrence_period")
    private String recurrencePeriod;

    @ApiModelProperty(value = "堤防长度")
    @TableField("length")
    private String length;

    @ApiModelProperty(value = "达到规划防洪(潮)标准的长度 (m)")
    @TableField("standard_length")
    private String standardLength;

    @ApiModelProperty(value = "高程系统")
    @TableField("altitude_system")
    private String altitudeSystem;

    @ApiModelProperty(value = "设计水（高潮）位 (m)")
    @TableField("design_level")
    private String designLevel;

    @ApiModelProperty(value = "堤防高度：（最大值） (m)")
    @TableField("height_max")
    private String heightMax;

    @ApiModelProperty(value = "堤防高度：（最小值） (m)")
    @TableField("height_min")
    private String heightMin;

    @ApiModelProperty(value = "堤顶宽度：（最大值） (m)")
    @TableField("width_max")
    private String widthMax;

    @ApiModelProperty(value = "堤顶宽度：（最小值） (m)")
    @TableField("width_min")
    private String widthMin;

    @ApiModelProperty(value = "工程任务")
    @TableField("engineering_task")
    private String engineeringTask;

    @ApiModelProperty(value = "坝顶起点高程")
    @TableField("start_altitude")
    private String startAltitude;

    @ApiModelProperty(value = "坝顶终点高程")
    @TableField("end_altitude")
    private String endAltitude;

    @ApiModelProperty(value = "描述")
    @TableField("description")
    private String description;

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
