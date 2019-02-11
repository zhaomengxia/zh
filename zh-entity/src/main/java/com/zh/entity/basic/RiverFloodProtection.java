package com.zh.entity.basic;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 中小型河流抗洪能力
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
@TableName("river_flood_protection")
@ApiModel(value="RiverFloodProtection对象", description="中小型河流抗洪能力")
public class RiverFloodProtection implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("id")
    private Long id;

    @TableField(exist = false)
    @Excel(name = "序号", width = 20)
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Integer serialNumber;

    @ApiModelProperty(value = "行政区域编码")
    @TableField("area_code")
    @Excel(name = "行政区划编码", width = 20)
    private String areaCode;

    @ApiModelProperty(value = "行政区域名称")
    @TableField("area_name")
    @Excel(name = "行政区域名称", width = 20)
    private String areaName;

    @ApiModelProperty(value = "河流名称")
    @TableField("river_name")
    @Excel(name = "河流名称", width = 20)
    private String riverName;

    @ApiModelProperty(value = "河流编码")
    @TableField("river_code")
    @Excel(name = "河流编码", width = 20)
    private String riverCode;

    @ApiModelProperty(value = "是否有堤防")
    @TableField("has_dike")
//    @Excel(name = "是否有堤防", width = 20)
    private Boolean hasDike;

    @ApiModelProperty(value = "是否有堤防")
    @TableField("has_dike")
    @Excel(name = "是否有堤防", width = 20)
    private String hasDikes;


    @ApiModelProperty(value = "控制断面名称")
    @TableField("section_name")
    @Excel(name = "控制断面名称", width = 20)
    private String sectionName;

    @ApiModelProperty(value = "控制断面编码")
    @TableField("section_code")
    @Excel(name = "控制断面编码", width = 20)
    private String sectionCode;

    @ApiModelProperty(value = "洪水频率")
    @TableField("flood_frequency")
    @Excel(name = "洪水频率", width = 20)
    private String floodFrequency;

    @ApiModelProperty(value = "水位")
    @TableField("level")
    @Excel(name = "水位(m)", width = 20)
    private String level;

    @ApiModelProperty(value = "流量")
    @TableField("flow")
    @Excel(name = "流量(m³/s)", width = 20)
    private String flow;

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
