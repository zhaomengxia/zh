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
 * 
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
@TableName("history_flood_record")
@ApiModel(value="HistoryFloodRecord对象", description="")
public class HistoryFloodRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @TableField(exist = false)
    @Excel(name = "序号", width = 20)
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Integer serialNumber;

    @ApiModelProperty(value = "行政区域名称")
    @TableField("area_name")
    @Excel(name = "行政区划名称", width = 20)
    private String areaName;

    @ApiModelProperty(value = "行政区域编码")
    @TableField("area_code")
    @Excel(name = "行政区划代码", width = 20)
    private String areaCode;


    @ApiModelProperty(value = "灾害发生地")
    @TableField("disaster_place")
    @Excel(name = "灾害发生地点", width = 20)
    private String disasterPlace;

    @ApiModelProperty(value = "灾害发生时间")
    @TableField("disaster_time")
    @Excel(name = "灾害发生日期", width = 20)
    private String disasterTime;

    @ApiModelProperty(value = "过程降雨量")
    @TableField("rainfall")
    @Excel(name = "过程降雨量(mm)", width = 20)
    private Double rainfall;

    @ApiModelProperty(value = "雨量站名称")
    @TableField("site_name")
    @Excel(name = "雨量站名称", width = 20)
    private String siteName;

    @ApiModelProperty(value = "经度")
    @TableField("longitude")
    @Excel(name = "经度", width = 20)
    private String longitude;

    @ApiModelProperty(value = "纬度")
    @TableField("latitude")
    @Excel(name = "纬度", width = 20)
    private String latitude;

    @ApiModelProperty(value = "管理公司")
    @TableField("manage_company")
    @Excel(name = "管理单位", width = 20)
    private String manageCompany;

    @ApiModelProperty(value = "最大流量")
    @TableField("max_flow")
    @Excel(name = "最大流量(m³/s)", width = 20)
    private String maxFlow;

    @ApiModelProperty(value = "最大淹没水深")
    @TableField("max_drown_depth")
    @Excel(name = "最大淹没水深(m)", width = 20)
    private String maxDrownDepth;

    @ApiModelProperty(value = "死亡人数")
    @TableField("death_toll")
    @Excel(name = "死亡人数(人)", width = 20)
    private Integer deathToll;

    @ApiModelProperty(value = "失踪人数")
    @TableField("missing_persons")
    @Excel(name = "失踪人数(人)", width = 20)
    private Integer missingPersons;

    @TableField("flood_hit_population")
    @Excel(name = "受灾人口(人)", width = 20)
    private Integer floodHitPopulation;

    @ApiModelProperty(value = "损毁房屋")
    @TableField("destoryed_house")
    @Excel(name = "损毁房屋(间)", width = 20)
    private Integer destoryedHouse;

    @ApiModelProperty(value = "直接经济损失")
    @TableField("pecuniary_loss")
    @Excel(name = "直接经济损失(万元)", width = 20)
    private Double pecuniaryLoss;

    @ApiModelProperty(value = "灾情描述")
    @TableField("description")
    private String description;

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
