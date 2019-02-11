package com.zh.entity.basic;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 圩区
 * </p>
 *
 * @author 赵梦霞
 * @since 2019-01-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("polder")
@ApiModel(value="Polder对象", description="圩区")
public class Polder implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @TableField(exist = false)
    @Excel(name = "序号", width = 20)
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Integer serialNumber;

    @ApiModelProperty(value = "行政区名称")
    @TableField("area_name")
    @Excel(name = "行政区名称", width = 20)
    private String areaName;

    @ApiModelProperty(value = "行政区编码")
    @TableField("area_code")
    @Excel(name = "行政区编码", width = 20)
    private String areaCode;

    @ApiModelProperty(value = "所在圩区")
    @TableField("polder_name")
    @Excel(name = "所在圩区", width = 20)
    private String polderName;

    @ApiModelProperty(value = "堤防长度")
    @TableField("dike_length")
    @Excel(name = "堤防长度(Km)", width = 20)
    private String dikeLength;

    @ApiModelProperty(value = "堤防高程")
    @TableField("dike_altitude")
    @Excel(name = "堤防高程(m)", width = 20)
    private String dikeAltitude;

    @ApiModelProperty(value = "堤防宽度")
    @TableField("dike_width")
    @Excel(name = "堤防宽度(m)", width = 20)
    private String dikeWidth;

    @ApiModelProperty(value = "圩区面积")
    @TableField("polder_area")
    @Excel(name = "圩区面积(Km²)", width = 20)
    private String polderArea;

    @ApiModelProperty(value = "庄台高程")
    @TableField("village_platform_altitude")
    @Excel(name = "庄台高程", width = 20)
    private String villagePlatformAltitude;

    @ApiModelProperty(value = "庄台面积")
    @TableField("village_platform_area")
    @Excel(name = "庄台面积(Km²)", width = 20)
    private String villagePlatformArea;

    @ApiModelProperty(value = "固定排涝动力台数")
    @TableField("pump_num")
    @Excel(name = "固定排涝动力台数", width = 20)
    private Integer pumpNum;

    @ApiModelProperty(value = "固定排涝动力功率")
    @TableField("pump_rate")
    @Excel(name = "固定排涝动力功率(KW)", width = 20)
    private String pumpRate;

    @ApiModelProperty(value = "总排涝流量")
    @TableField("drainage_flow")
    @Excel(name = "总排涝流量(m³/s)", width = 20)
    private String drainageFlow;

    @ApiModelProperty(value = "排涝模数")
    @TableField("drainage_rate")
    @Excel(name = "排涝模数(m³/s·万亩)", width = 20)
    private String drainageRate;

    @ApiModelProperty(value = "涵洞")
    @TableField("culvert")
    @Excel(name = "涵洞", width = 20)
    private String culvert;

    @ApiModelProperty(value = "闸")
    @TableField("gate")
    @Excel(name = "闸", width = 20)
    private String gate;

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
