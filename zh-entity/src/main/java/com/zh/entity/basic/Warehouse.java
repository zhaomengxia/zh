package com.zh.entity.basic;

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
 * @since 2018-12-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("warehouse")
@ApiModel(value = "Warehouse对象", description = "")
public class Warehouse implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "物资存放点主键")
    @TableId(value = "id", type = IdType.ID_WORKER)
    private Long id;

    @ApiModelProperty(value = "物资存放点名称")
    @TableField("ware_name")
    private String wareName;

    @ApiModelProperty(value = "所属行政区代码")
    @TableField("area_code")
    private String areaCode;

    @ApiModelProperty(value = "所属行政区名称")
    @TableField(exist = false)
    private String areaName;

    @ApiModelProperty(value = "负责人")
    @TableField("principal")
    private String principal;

    @ApiModelProperty(value = "联系方式")
    @TableField("telephone")
    private String telephone;

    @ApiModelProperty(value = "地址")
    @TableField("address")
    private String address;

    @JsonIgnore
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @ApiModelProperty(hidden = true)
    private LocalDateTime createTime;

    @JsonIgnore
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(hidden = true)
    private LocalDateTime updateTime;

    @JsonIgnore
    @TableField(value = "is_deleted", fill = FieldFill.INSERT_UPDATE)
    @TableLogic
    @ApiModelProperty(hidden = true)
    private Boolean deleted;


}
