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
 * 自动监测站
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
@TableName("auto_station")
@ApiModel(value="AutoStation对象", description="自动监测站")
public class AutoStation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @TableField(exist = false)
    @Excel(name = "序号", width = 20)
    @JsonIgnore
    @ApiModelProperty(hidden = true)
    private Integer serialNumber;

    @ApiModelProperty(value = "站点编码")
    @TableField("site_code")
    @Excel(name = "站点编码", width = 20)
    private String siteCode;

    @ApiModelProperty(value = "站点名称")
    @TableField("site_name")
    @Excel(name = "站点名称", width = 20)
    private String siteName;

    @ApiModelProperty(value = "河流名称")
    @TableField("river_name")
    @Excel(name = "河流名称", width = 20)
    private String riverName;

    @ApiModelProperty(value = "水系名称")
    @TableField("drainage")
    @Excel(name = "水系名称", width = 20)
    private String drainage;

    @ApiModelProperty(value = "流域")
    @TableField("basin")
    @Excel(name = "流域", width = 20)
    private String basin;

    @ApiModelProperty(value = "经度")
    @TableField("longitude")
    @Excel(name = "经度(°)", width = 20)
    private String longitude;

    @ApiModelProperty(value = "纬度")
    @TableField("latitude")
    @Excel(name = "纬度(°)", width = 20)
    private String latitude;

    @ApiModelProperty(value = "站址")
    @TableField("address")
    @Excel(name = "站址", width = 20)
    private String address;

    @ApiModelProperty(value = "行政区域编码")
    @TableField("area_code")
    @Excel(name = "行政区域编码", width = 20)
    private String areaCode;

    @ApiModelProperty(value = "基面名称")
    @TableField("datum_name")
    @Excel(name = "基面名称", width = 20)
    private String datumName;

    @ApiModelProperty(value = "基面高程")
    @TableField("datum_altitude")
    @Excel(name = "基面高程(m)", width = 20)
    private String datumAltitude;

    @ApiModelProperty(value = "基面修正值")
    @TableField("datum_correction_value")
    @Excel(name = "基面修正值", width = 20)
    private Double datumCorrectionValue;

    @ApiModelProperty(value = "站类")
    @TableField("type")
    @Excel(name = "站类", width = 20)
    private String type;

    @ApiModelProperty(value = "报讯等级")
    @TableField("new_report_degree")
    @Excel(name = "报讯等级", width = 20)
    private String newReportDegree;

    @ApiModelProperty(value = "建站年月")
    @TableField("build_time")
    @Excel(name = "建站年月", width = 20)
    private String buildTime;

    @ApiModelProperty(value = "始报年月")
    @TableField("initial_report_time")
    @Excel(name = "始报年月", width = 20)
    private String initialReportTime;

    @ApiModelProperty(value = "所属行业单位")
    @TableField("industry_company")
    @Excel(name = "所属行业单位", width = 20)
    private String industryCompany;

    @ApiModelProperty(value = "信息管理单位")
    @TableField("information_manage_company")
    @Excel(name = "所属行业单位", width = 20)
    private String informationManageCompany;

    @ApiModelProperty(value = "交换管理单位")
    @TableField("change_manage_company")
    @Excel(name = "交换管理单位", width = 20)
    private String changeManageCompany;

    @ApiModelProperty(value = "测点岸别")
    @TableField("shore_separation")
    @Excel(name = "测点岸别", width = 20)
    private String shoreSeparation;

    @ApiModelProperty(value = "测站方位")
    @TableField("position")
    @Excel(name = "测站方位(°)", width = 20)
    private String position;

    @ApiModelProperty(value = "至河口距离")
    @TableField("river_distance")
    @Excel(name = "至河口距离(Km)", width = 20)
    private String riverDistance;

    @ApiModelProperty(value = "集水面积")
    @TableField("catchment_area")
    @Excel(name = "集水面积(Km²)", width = 20)
    private String catchmentArea;

    @ApiModelProperty(value = "拼音码")
    @TableField("pinyin_code")
    @Excel(name = "拼音码", width = 20)
    private String pinyinCode;

    @ApiModelProperty(value = "启用标志")
    @TableField("enable_logo")
    @Excel(name = "启用标志", width = 20)
    private String enableLogo;

    @ApiModelProperty(value = "备注")
    @TableField("content")
    @Excel(name = "备注", width = 20)
    private String content;

    @ApiModelProperty(value = "时间戳")
    @TableField("time_stamp")
    @Excel(name = "时间戳", width = 20)
    private String timeStamp;

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
