package com.zh.entity.sys;

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
 * @since 2018-12-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("site")
@ApiModel(value = "Site对象", description = "自动监测站")
public class Site implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "站点名称")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "泵站编码")
    @TableField("code")
    private String code;

    @ApiModelProperty(value = "建设单位")
    @TableField("constraction_company")
    private String constractionCompany;

    @ApiModelProperty(value = "管理单位")
    @TableField("manage_company")
    private String manageCompany;

    @ApiModelProperty(value = "通讯方式")
    @TableField("telephone")
    private String telephone;

    @ApiModelProperty(value = "所属行政区")
    @TableField("area_code")
    private String areaCode;

    @ApiModelProperty(value = "站点地址")
    @TableField("address")
    private String address;

    @ApiModelProperty(value = "经度")
    @TableField("longitude")
    private String longitude;

    @ApiModelProperty(value = "纬度")
    @TableField("latitude")
    private String latitude;

    @ApiModelProperty(value = "泵站实景图地址")
    @TableField("photo")
    private String photo;

    @ApiModelProperty(value = "历史最高水位")
    @TableField("record_level")
    private Double recordLevel;

    @ApiModelProperty(value = "历史最高水位出现时间")
    @TableField("record_time")
    private LocalDateTime recordTime;

    @ApiModelProperty("是否重点")
    @TableField("is_focus")
    private Boolean isFocus;

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
